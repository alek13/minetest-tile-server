package io.rudin.minetest.tileserver.job;

import static io.rudin.minetest.tileserver.blockdb.tables.Blocks.BLOCKS;
import static io.rudin.minetest.tileserver.tiledb.tables.Tiles.TILES;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.rudin.minetest.tileserver.TileRenderer;
import io.rudin.minetest.tileserver.accessor.BlocksRecordAccessor;
import io.rudin.minetest.tileserver.accessor.Coordinate;
import io.rudin.minetest.tileserver.accessor.MapBlockAccessor;
import io.rudin.minetest.tileserver.blockdb.tables.records.BlocksRecord;
import io.rudin.minetest.tileserver.config.Layer;
import io.rudin.minetest.tileserver.config.LayerConfig;
import io.rudin.minetest.tileserver.config.TileServerConfig;
import io.rudin.minetest.tileserver.qualifier.TileDB;
import io.rudin.minetest.tileserver.query.YQueryBuilder;
import io.rudin.minetest.tileserver.service.EventBus;
import org.jooq.*;

import io.rudin.minetest.tileserver.service.TileCache;
import io.rudin.minetest.tileserver.util.CoordinateResolver;
import io.rudin.minetest.tileserver.util.CoordinateResolver.TileInfo;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class UpdateChangedTilesJob implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(UpdateChangedTilesJob.class);

	static final Counter changedTileCounter = Counter.build()
			.name("tileserver_changed_tiles_total").help("Total changed tiles.").register();

	static final Gauge changedTiles = Gauge.build()
			.name("tileserver_changed_tiles")
			.help("Changed tiles in update job")
			.register();

	static final Map<Integer, Gauge> layerBlockChangeGaugeMap = new HashMap<>();

	static final Map<Integer, Gauge> layerBlockQueryTimingGaugeMap = new HashMap<>();


	@Inject
	public UpdateChangedTilesJob(DSLContext ctx, TileCache tileCache, EventBus eventBus, TileServerConfig cfg,
								 MapBlockAccessor mapBlockAccessor, BlocksRecordAccessor blocksRecordAccessor,
								 TileRenderer tileRenderer, YQueryBuilder yQueryBuilder, LayerConfig layerCfg) {
		this.ctx = ctx;
		this.tileCache = tileCache;
		this.eventBus = eventBus;
		this.tileRenderer = tileRenderer;

		this.yQueryBuilder = yQueryBuilder;
		this.layerCfg = layerCfg;
		this.cfg = cfg;

		this.mapBlockAccessor = mapBlockAccessor;
		this.blocksRecordAccessor = blocksRecordAccessor;

		for (Layer layer: layerCfg.layers){
			layerBlockChangeGaugeMap.put(layer.id, Gauge.build()
					.name("tileserver_changed_blocks_layer_" + layer.id)
					.help("Changed blocks in update job for layer " + layer.name)
					.register()
			);
			layerBlockQueryTimingGaugeMap.put(layer.id, Gauge.build()
					.name("tileserver_changed_blocks_timing_layer_" + layer.id)
					.help("Changed blocks query timing in update job for layer " + layer.name)
					.register()
			);
		}
	}

	private final YQueryBuilder yQueryBuilder;

	private final LayerConfig layerCfg;

	private final TileRenderer tileRenderer;

	private final MapBlockAccessor mapBlockAccessor;

	private final BlocksRecordAccessor blocksRecordAccessor;

	private final TileServerConfig cfg;

	private final EventBus eventBus;

	private final DSLContext ctx;
	
	private final TileCache tileCache;

	private boolean running = false;

	private String getTileKey(TileInfo tile){
		return "Tile:" + tile.x + "/" + tile.y + "/" + tile.zoom;
	}

	static final Histogram changedTilesTime = Histogram.build()
			.name("tileserver_update_changed_tiles_time_seconds").help("Tile update job time in seconds.").register();

	private final Map<Integer, Long> timestampMap = new HashMap<>();

	@Override
	public void run() {

		if (running) {
			//skip multiple invocations
			return;
		}

		if (timestampMap.isEmpty()) {

			long latestTimestamp = 0L;

			if (cfg.tilerendererEnableInitialRendering()){
				logger.info("Initial rendering detected, ignoring updated blocks since now");
				latestTimestamp = System.currentTimeMillis();

			} else {

				logger.debug("Gathering latest tile time from tile cache");
				long start = System.currentTimeMillis();

				latestTimestamp = tileCache.getLatestTimestamp();

				long diff = System.currentTimeMillis() - start;

				logger.info("Newest tile time is {} / {}", latestTimestamp, new Date(latestTimestamp));

				if (diff > 1000){
					logger.warn("Tile time fetch took {} ms", diff);
				}
			}

			for (Layer layer: layerCfg.layers){
				timestampMap.put(layer.id, latestTimestamp);
			}

		}

		Histogram.Timer timer = null;
		long tileCount = 0;

		try {

			timer = changedTilesTime.startTimer();

			long start = System.currentTimeMillis();

			running = true;
			final int LIMIT = cfg.tilerendererUpdateMaxBlocks();

			for (Layer layer: layerCfg.layers) {

				long latestTimestamp = timestampMap.get(layer.id);

				logger.debug("update for layer: {}", layer.name);

				long t0 = System.currentTimeMillis();

				Condition yCondition = yQueryBuilder.getCondition(layer);

				Result<BlocksRecord> blocks = ctx
						.selectFrom(BLOCKS)
						.where(BLOCKS.MTIME.gt(latestTimestamp))
						.and(yCondition)
						.orderBy(BLOCKS.MTIME.asc()) //oldest first
						.limit(LIMIT)
						.fetch();

				long queryTime = System.currentTimeMillis() - t0;

				int count = blocks.size();
				int invalidatedTiles = 0;

				layerBlockQueryTimingGaugeMap.get(layer.id).set(queryTime);
				layerBlockChangeGaugeMap.get(layer.id).set(count);

				long diff = System.currentTimeMillis() - start;

				boolean renderImmediately = cfg.tileRenderingStrategy() == TileServerConfig.TileRenderingStrategy.ASAP;

				if (diff > 500 && cfg.logQueryPerformance()) {
					logger.warn("updated-tiles-query took {} ms", diff);
				} else {
					logger.debug("updated-tiles-query took {} ms", diff);
				}

				if (blocks.size() == LIMIT) {
					logger.warn("Got max-blocks ({}) from update-queue", LIMIT);

					if (renderImmediately) {
						logger.warn("Disabling immediate rendering strategy for current run!");
						renderImmediately = false;
					}
				}

				logger.debug("Got {} updated blocks", blocks.size());

				List<String> updatedTileKeys = new ArrayList<>();

				for (BlocksRecord record : blocks) {

					blocksRecordAccessor.update(record);
					mapBlockAccessor.invalidate(new Coordinate(record));

					Integer x = record.getPosx();
					Integer z = record.getPosz();

					if (record.getMtime() > latestTimestamp) {
						//Update timestamp
						latestTimestamp = record.getMtime();
					}

					TileInfo tileInfo = CoordinateResolver.fromCoordinates(x, z);

					//remove all tiles in every zoom
					for (int i = CoordinateResolver.MAX_ZOOM; i >= CoordinateResolver.MIN_ZOOM; i--) {
						TileInfo zoomedTile = tileInfo.toZoom(i);
						String tileKey = getTileKey(zoomedTile);

						if (!updatedTileKeys.contains(tileKey)) {
							invalidatedTiles++;
							tileCache.remove(layer.id, zoomedTile.x, zoomedTile.y, zoomedTile.zoom);
							tileCount++;

							updatedTileKeys.add(tileKey);
						}

					}
				}

				logger.debug("Finished invalidating changed tiles");

				//assign new timestamp
				timestampMap.put(layer.id, latestTimestamp);

				updatedTileKeys.clear();

				logger.debug("Starting rendering of changed tiles");

				//Second run with rendering
				for (BlocksRecord record : blocks) {

					Integer x = record.getPosx();
					Integer z = record.getPosz();

					TileInfo tileInfo = CoordinateResolver.fromCoordinates(x, z);

					for (int i = CoordinateResolver.MAX_ZOOM; i >= CoordinateResolver.MIN_ZOOM+2; i--) {
						TileInfo zoomedTile = tileInfo.toZoom(i);
						String tileKey = getTileKey(zoomedTile);

						if (!updatedTileKeys.contains(tileKey)) {

							if (renderImmediately) {
								//Generate tiles now
								logger.debug("Rendering tile x={} y={} zoom={}", zoomedTile.x, zoomedTile.y, zoomedTile.zoom);
								tileRenderer.render(layer, zoomedTile.x, zoomedTile.y, zoomedTile.zoom);
							}

							logger.debug("Dispatching tile-changed-event for tile: {}/{}", zoomedTile.x, zoomedTile.y);

							EventBus.TileChangedEvent event = new EventBus.TileChangedEvent();
							event.layerId = layer.id;
							event.x = zoomedTile.x;
							event.y = zoomedTile.y;
							event.zoom = zoomedTile.zoom;
							event.mapblockX = x;
							event.mapblockZ = z;
							eventBus.post(event);

							updatedTileKeys.add(tileKey);
						}

					}

				}

				final String msg = "Tile update job took {} ms for {} blocks in layer: '{}' (invalidated {} tiles)";
				final Object[] params = new Object[]{
						System.currentTimeMillis() - start,
						count,
						layer.name,
						invalidatedTiles
				};

				if (cfg.logTileUpdateTimings())
					logger.info(msg, params);
				else
					logger.debug(msg, params);
			}

		} catch(Exception e){
			logger.error("tile-updater", e);

		} finally {
			changedTiles.set(tileCount);

			running = false;
			timer.observeDuration();
		}

	}

}
