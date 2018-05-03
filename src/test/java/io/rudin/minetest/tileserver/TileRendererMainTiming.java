package io.rudin.minetest.tileserver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.rudin.minetest.tileserver.config.TileServerConfig;
import io.rudin.minetest.tileserver.module.ConfigModule;
import io.rudin.minetest.tileserver.module.DBModule;
import io.rudin.minetest.tileserver.module.ServiceModule;
import io.rudin.minetest.tileserver.service.TileCache;

public class TileRendererMainTiming {
    private static Injector injector = Guice.createInjector(
            new ConfigModule(),
            new DBModule(),
            new ServiceModule()
    );

    public static void main(String[] args) throws Exception {

        TileServerConfig cfg = injector.getInstance(TileServerConfig.class);

        DBMigration dbMigration = injector.getInstance(DBMigration.class);
        dbMigration.migrate();

        int x = 0;
        int y = 0;
        int zoom = 9; //13;

        TileRenderer renderer = injector.getInstance(TileRenderer.class);
        TileCache cache = injector.getInstance(TileCache.class);

        cache.remove(x,y,zoom);

        long start = System.currentTimeMillis();
        renderer.renderImage(x,y,zoom);
        long diff = System.currentTimeMillis() - start;

        System.out.println("render took " + diff + " ms");



    }
}