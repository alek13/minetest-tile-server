package io.rudin.minetest.tileserver.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"file:./tileserver.properties"
})
public interface TileServerConfig extends Config {

	@Key("http.port")
	@DefaultValue("8080")
	int httPort();

	//in map blocks
	@Key("tiles.maxy")
	@DefaultValue("10")
	int tilesMaxY();

	@Key("tiles.miny")
	@DefaultValue("-1")
	int tilesMinY();

	/*
	Tile renderer stuff
	 */

	//This should only be enabled once after a fresh install
	@Key("tilerenderer.initialrendering.enable")
	@DefaultValue("false")
	boolean tilerendererEnableInitialRendering();

	@Key("tilerenderer.updateinterval")
	@DefaultValue("20")
	int tilerendererUpdateInterval();

	@Key("tilerenderer.maxupdateblocks")
	@DefaultValue("500")
	int tilerendererUpdateMaxBlocks();

	@Key("player.updateinterval")
	@DefaultValue("2")
	int playerUpdateInterval();

	@Key("tile.route.reentrycount")
	@DefaultValue("5000")
	int tileRouteReentryCount();

	/*
	Logging stuff
	 */

	@Key("log.query.performance")
	@DefaultValue("false")
	boolean logQueryPerformance();

	@Key("log.tile.updatetimings")
	@DefaultValue("false")
	boolean logTileUpdateTimings();

	@Key("debug.mapblock.dumpinvalid")
	@DefaultValue("false")
	boolean dumpInvalidMapblockData();

	/*
	POI stuff
	 */

	@Key("poi.enable")
	@DefaultValue("false")
	boolean poiEnable();

	/*
	tile cache stuff
	 */

	@Key("tile.cache.impl")
	@DefaultValue("EHCACHE")
	CacheType tileCacheType();

	enum CacheType {
		DATABASE,
		FILE,
		EHCACHE
	}

	@Key("tiles.directory")
	@DefaultValue("tiles")
	String tileDirectory();

	/*
	 Matomo tracker id
	 */
	@Key("tracker.matomo.id")
	String matomoTrackerId();

	@Key("tracker.matomo.url")
	String matomoTrackerUrl();

	/*
	 Default minetest db
	 */

	@Key("minetest.db.url")
	@DefaultValue("jdbc:postgresql://127.0.0.1:5432/minetest")
	String minetestDatabaseUrl();

	@Key("minetest.db.username")
	@DefaultValue("postgres")
	String minetestDatabaseUsername();
	
	@Key("minetest.db.password")
	@DefaultValue("enter")
	String minetestDatabasePassword();
	
	@Key("minetest.db.driver")
	@DefaultValue("org.postgresql.Driver")
	String minetestDatabaseDriver();

	/*
	 Tile cache DB
	 */

	@Key("tile.db.url")
	@DefaultValue("jdbc:postgresql://127.0.0.1:5432/tiles")
	String tileDatabaseUrl();

	@Key("tile.db.username")
	@DefaultValue("postgres")
	String tileDatabaseUsername();

	@Key("tile.db.password")
	@DefaultValue("enter")
	String tileDatabasePassword();

	@Key("tile.db.driver")
	@DefaultValue("org.postgresql.Driver")
	String tileDatabaseDriver();

}
