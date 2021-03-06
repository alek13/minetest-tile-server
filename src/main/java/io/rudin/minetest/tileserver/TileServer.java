package io.rudin.minetest.tileserver;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import ch.qos.logback.classic.Level;
import com.google.inject.Guice;
import com.google.inject.Injector;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import io.rudin.minetest.tileserver.config.TileServerConfig;
import io.rudin.minetest.tileserver.job.InitialTileRendererJob;
import io.rudin.minetest.tileserver.job.UpdateChangedTilesJob;
import io.rudin.minetest.tileserver.job.UpdatePlayerJob;
import io.rudin.minetest.tileserver.listener.*;
import io.rudin.minetest.tileserver.module.ConfigModule;
import io.rudin.minetest.tileserver.module.DBModule;
import io.rudin.minetest.tileserver.module.ServiceModule;
import io.rudin.minetest.tileserver.route.*;
import io.rudin.minetest.tileserver.transformer.JsonTransformer;
import io.rudin.minetest.tileserver.ws.WebSocketHandler;
import io.rudin.minetest.tileserver.ws.WebSocketUpdater;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class TileServer {

	private static final Logger logger = LoggerFactory.getLogger(TileServer.class);

	private static TileServerConfig cfg;

	private static Injector injector;
	
	public static void main(String[] args) throws Exception {

		cfg = ConfigFactory.create(TileServerConfig.class);

		if (cfg.enableDebug()){
			TileServer.logger.warn("Enabling debug/logging mode");

			Logger logger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
			ch.qos.logback.classic.Logger loggerImpl = (ch.qos.logback.classic.Logger)logger;

			loggerImpl.setLevel(Level.DEBUG);
		}

		injector = Guice.createInjector(
				new ConfigModule(cfg),
				new DBModule(cfg),
				new ServiceModule(cfg)
		);
		
		DBMigration dbMigration = injector.getInstance(DBMigration.class);
		dbMigration.migrate();

		if (cfg.staticFilesLocation().isEmpty()) {
			//Prod
			staticFileLocation("/public");

		} else {
			//Static files specified, DEV mode
			TileServer.logger.warn("Using external static file location: '{}'", cfg.staticFilesLocation());
			externalStaticFileLocation(cfg.staticFilesLocation());

		}

		port(cfg.httPort());

		HTTPServer promServer = null;
		if (cfg.prometheusEnable()) {
			TileServer.logger.info("Starting prometheus metrics server at port {}", cfg.prometheusPort());
			promServer = new HTTPServer(cfg.prometheusPort());
			DefaultExports.initialize();
		}

		JsonTransformer json = injector.getInstance(JsonTransformer.class);

		webSocket("/ws", WebSocketHandler.class);
		get("/tiles/:layerid/:z/:x/:y", injector.getInstance(TileRoute.class));
		get("/player", injector.getInstance(PlayerRoute.class), json);
		get("/config", injector.getInstance(ConfigRoute.class), json);
		get("/layers", injector.getInstance(LayerConfigRoute.class), json);
		get("/stats/unknownblocks", injector.getInstance(UnknownBlocksRoute.class), json);

		get("/shop", injector.getInstance(ShopRoute.class), json);
		get("/travelnet", injector.getInstance(TravelnetRoute.class), json);
		get("/missions", injector.getInstance(MissionsRoute.class), json);
		get("/poi", injector.getInstance(PoiRoute.class), json);
		get("/protector/:layerId/:x/:z", injector.getInstance(ProtectorRoute.class), json);
		get("/trainline", injector.getInstance(TrainlineRoute.class), json);


		//Initialize ws updater
		injector.getInstance(WebSocketUpdater.class).init();

		//Register listener mapblock listener
		if (cfg.parserPoiEnable()) {
			injector.getInstance(PoiMapBlockListener.class).setup();
		}

		if (cfg.parserMissionsEnable()){
			injector.getInstance(MissionBlockListener.class).setup();
		}

		if (cfg.parserTravelnetEnable()){
			injector.getInstance(TravelNetBlockListener.class).setup();
		}

		if (cfg.parserSmartshopEnable()){
			injector.getInstance(SmartShopBlockListener.class).setup();
		}

		if (cfg.parserFancyVendEnable()){
			injector.getInstance(FancyVendBlockListener.class).setup();
		}

		if (cfg.parserProtectorEnable()){
			injector.getInstance(ProtectorMapBlockListener.class).setup();
		}

		if (cfg.parserTrainEnable())
			injector.getInstance(TrainBlockListener.class).setup();

		ScheduledExecutorService executor = injector.getInstance(ScheduledExecutorService.class);


		if (cfg.tilerendererEnableInitialRendering()){
			//Start initial rendering
			executor.submit(injector.getInstance(InitialTileRendererJob.class));
		} else {
			startUpdateJobs();
			startWebserver();
		}

		//TODO: do this properly, join or something
		AtomicBoolean running = new AtomicBoolean(true);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			stop();
			running.set(false);
		}));

		while (running.get()){
			Thread.sleep(500);
		}

		if (cfg.prometheusEnable())
			promServer.stop();
	}

	public static void startWebserver(){
		//Initialize web server
		logger.info("Starting webserver");
		init();
	}

	public static void startUpdateJobs(){
		logger.info("Starting update jobs");
		ScheduledExecutorService executor = injector.getInstance(ScheduledExecutorService.class);

		//Start normal tasks
		executor.scheduleAtFixedRate(injector.getInstance(UpdateChangedTilesJob.class), 0, cfg.tilerendererUpdateInterval(), TimeUnit.SECONDS);
		executor.scheduleAtFixedRate(injector.getInstance(UpdatePlayerJob.class), 0, cfg.playerUpdateInterval(), TimeUnit.SECONDS);
	}
	
}
