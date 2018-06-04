package io.rudin.minetest.tileserver.job;

import static io.rudin.minetest.tileserver.blockdb.tables.Blocks.BLOCKS;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.rudin.minetest.tileserver.blockdb.tables.records.BlocksRecord;
import io.rudin.minetest.tileserver.service.EventBus;
import org.jooq.Cursor;
import org.jooq.DSLContext;
import org.jooq.Record2;

import io.rudin.minetest.tileserver.service.TileCache;
import io.rudin.minetest.tileserver.util.CoordinateResolver;
import io.rudin.minetest.tileserver.util.CoordinateResolver.TileInfo;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

@Singleton
public class UpdateChangedTilesJob implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(UpdateChangedTilesJob.class);

	@Inject
	public UpdateChangedTilesJob(DSLContext ctx, TileCache tileCache, EventBus eventBus) {
		this.ctx = ctx;
		this.tileCache = tileCache;
		this.eventBus = eventBus;
	}

	private final EventBus eventBus;

	private final DSLContext ctx;
	
	private final TileCache tileCache;

	private boolean running = false;

	@Override
	public void run() {

		if (running) {
			//skip multiple invocations
			return;
		}

		try {
			running = true;

			Timestamp now = new Timestamp(System.currentTimeMillis());

			Result<BlocksRecord> blocks = ctx
					.selectFrom(BLOCKS)
					.where(BLOCKS.MTIME.ge(now))
					.limit(200)
					.fetch();

			if (blocks.size() == 200){
				logger.warn("Got max-blocks from update-queue");
			}

			for (BlocksRecord record: blocks) {
				Integer x = record.getPosx();
				Integer z = record.getPosz();


				TileInfo tileInfo = CoordinateResolver.fromCoordinates(x, z);

				//remove all tiles in every zoom
				for (int i=CoordinateResolver.MAX_ZOOM; i>=CoordinateResolver.MIN_ZOOM; i--) {
					TileInfo zoomedTile = tileInfo.toZoom(i);

					EventBus.TileChangedEvent event = new EventBus.TileChangedEvent();
					event.x = zoomedTile.x;
					event.y = zoomedTile.y;
					event.zoom = zoomedTile.zoom;
					event.mapblockX = x;
					event.mapblockZ = z;
					eventBus.post(event);

					tileCache.remove(zoomedTile.x, zoomedTile.y, zoomedTile.zoom);
				}


			}


		} finally {
			running = false;

		}

	}

}
