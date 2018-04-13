package io.rudin.minetest.tileserver;

import static io.rudin.minetest.tileserver.blockdb.tables.Blocks.BLOCKS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

import javax.imageio.ImageIO;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rudin.minetest.tileserver.blockdb.tables.records.BlocksRecord;
import io.rudin.minetest.tileserver.cache.TileCache;
import io.rudin.minetest.util.CoordinateResolver;
import io.rudin.minetest.util.CoordinateResolver.MapBlockCoordinateInfo;

public class TileRenderer {

	private static final Logger logger = LoggerFactory.getLogger(TileRenderer.class);
	
	public TileRenderer(DSLContext ctx, ColorTable colorTable, TileCache cache) {
		this.ctx = ctx;
		this.colorTable = colorTable;
		this.cache = cache;
	}

	private final TileCache cache;

	private final DSLContext ctx;

	private final ColorTable colorTable;

	public BufferedImage createTile() {
		return new BufferedImage(CoordinateResolver.TILE_PIXEL_SIZE, CoordinateResolver.TILE_PIXEL_SIZE, BufferedImage.TYPE_INT_RGB);
	}

	public byte[] render(int tileX, int tileY, int zoom) throws IllegalArgumentException, DataFormatException, IOException {

		//Check binary cache
		if (cache.has(tileX, tileY, zoom)) {
			return cache.get(tileX, tileY, zoom);
		}


		BufferedImage image = renderImage(tileX, tileY, zoom);

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(image, "png", output);

		byte[] data = output.toByteArray();

		cache.put(tileX, tileY, zoom, data);

		return data;

	}


	public BufferedImage renderImage(int tileX, int tileY, int zoom) throws IllegalArgumentException, DataFormatException, IOException {

		//Check if binary cached, use cached version for rendering
		if (cache.has(tileX, tileY, zoom)) {
			byte[] data = cache.get(tileX, tileY, zoom);
			return ImageIO.read(new ByteArrayInputStream(data));
		}
		
		final int HALF_TILE_PIXEL_SIZE = CoordinateResolver.TILE_PIXEL_SIZE  / 2;


		if (zoom > 9) {
			//Zoom in

			int nextZoom = zoom - 1;
			int offsetNextZoomX = Math.abs(tileX % 2);
			int offsetNextZoomY = Math.abs(tileY % 2);

			int nextZoomX = (tileX - offsetNextZoomX) / 2;
			int nextZoomY = (tileY - offsetNextZoomY) / 2;

			BufferedImage tile = createTile();
			Graphics2D graphics = tile.createGraphics();

			//Get all quadrants
			BufferedImage image = renderImage(nextZoomX, nextZoomY, nextZoom);
			
			BufferedImage quadrant = image.getSubimage(
					HALF_TILE_PIXEL_SIZE * offsetNextZoomX,
					HALF_TILE_PIXEL_SIZE * offsetNextZoomY,
					HALF_TILE_PIXEL_SIZE,
					HALF_TILE_PIXEL_SIZE
			);
			
			Image scaledInstance = quadrant.getScaledInstance(
					CoordinateResolver.TILE_PIXEL_SIZE,
					CoordinateResolver.TILE_PIXEL_SIZE,
					Image.SCALE_FAST
			);
			
			graphics.drawImage(scaledInstance, 0, 0, CoordinateResolver.TILE_PIXEL_SIZE, CoordinateResolver.TILE_PIXEL_SIZE, null);
			
			return tile;


		} else if (zoom < 9) {
			//Zoom out


			BufferedImage tile = createTile();
			//Pack 4 tiles from higher zoom into 1 tile

			int nextZoom = zoom + 1;
			int nextZoomX = tileX * 2;
			int nextZoomY = tileY * 2;

			Graphics2D graphics = tile.createGraphics();


			//upper left
			BufferedImage image = renderImage(nextZoomX, nextZoomY, nextZoom);
			Image scaledInstance = image.getScaledInstance(HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, Image.SCALE_FAST);
			graphics.drawImage(scaledInstance, 0, 0, HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, null);

			//upper right
			image = renderImage(nextZoomX+1, nextZoomY, nextZoom);
			scaledInstance = image.getScaledInstance(HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, Image.SCALE_FAST);
			graphics.drawImage(scaledInstance, HALF_TILE_PIXEL_SIZE, 0, HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, null);

			//lower left
			image = renderImage(nextZoomX, nextZoomY+1, nextZoom);
			scaledInstance = image.getScaledInstance(HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, Image.SCALE_FAST);
			graphics.drawImage(scaledInstance, 0, HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, null);

			//lower right
			image = renderImage(nextZoomX+1, nextZoomY+1, nextZoom);
			scaledInstance = image.getScaledInstance(HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, Image.SCALE_FAST);
			graphics.drawImage(scaledInstance, HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, HALF_TILE_PIXEL_SIZE, null);

			return tile;

		}

		//Default zoom (9)

		MapBlockCoordinateInfo coordinateInfo = CoordinateResolver.fromTile(tileX, tileY, zoom);

		//16x16 mapblocks on a tile
		BufferedImage image = createTile();
		Graphics2D graphics = image.createGraphics();

		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, CoordinateResolver.TILE_PIXEL_SIZE, CoordinateResolver.TILE_PIXEL_SIZE);

		for (int mbx=0; mbx<16; mbx++) {
			for (int mbz=0; mbz<16; mbz++) {

				Graphics mapblockGraphics = graphics.create(
						mbx * CoordinateResolver.MAPBLOCK_PIXEL_SIZE,
						mbz * CoordinateResolver.MAPBLOCK_PIXEL_SIZE,
						CoordinateResolver.MAPBLOCK_PIXEL_SIZE,
						CoordinateResolver.MAPBLOCK_PIXEL_SIZE);

				int mapblockX = coordinateInfo.x + mbx;
				int mapblockZ = coordinateInfo.z + (mbz *-1);

				Result<BlocksRecord> blocks = ctx
						.selectFrom(BLOCKS)
						.where(
								BLOCKS.POSX.eq(mapblockX)
								.and(BLOCKS.POSZ.eq(mapblockZ))
								)
						.orderBy(BLOCKS.POSY.desc())
						.fetch();

				if (blocks.isEmpty())
					continue;

				MapBlockRenderer renderer = new MapBlockRenderer(mapblockGraphics, colorTable);

				renderer.render(blocks);
			}
		}

		return image;
	}

}