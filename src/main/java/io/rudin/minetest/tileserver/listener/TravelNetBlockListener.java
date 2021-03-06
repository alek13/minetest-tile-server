package io.rudin.minetest.tileserver.listener;

import com.google.common.eventbus.Subscribe;
import io.rudin.minetest.tileserver.util.MapBlock;
import io.rudin.minetest.tileserver.blockdb.tables.records.TravelnetRecord;
import io.rudin.minetest.tileserver.parser.Metadata;
import io.rudin.minetest.tileserver.qualifier.MapDB;
import io.rudin.minetest.tileserver.service.EventBus;
import org.jooq.DSLContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Optional;

import static io.rudin.minetest.tileserver.blockdb.tables.Travelnet.TRAVELNET;

@Singleton
public class TravelNetBlockListener {

    private static final String BLOCK_NAME = "travelnet:travelnet";

    @Inject
    public TravelNetBlockListener(EventBus eventBus, @MapDB DSLContext ctx){
        this.eventBus = eventBus;
        this.ctx = ctx;
    }

    private final DSLContext ctx;

    private final EventBus eventBus;

    public void setup(){
        eventBus.register(this);
    }

    private void register(MapBlock mapBlock, int x, int y, int z){
        Metadata metadata = mapBlock.getMetadata();

        int position = MapBlock.toPosition(x, y, z);

        Map<String, String> map = metadata.map.get(position);

        int posx = (mapBlock.x * 16) + x;
        int posy = (mapBlock.y * 16) + y;
        int posz = (mapBlock.z * 16) + z;

        String station = map.get("station_name");
        String network = map.get("station_network");

        if (station == null || network == null)
            //unconfigured
            return;

        TravelnetRecord record = ctx.newRecord(TRAVELNET);
        record.setName(station);
        record.setOwner(map.get("owner"));
        record.setNetwork(network);
        record.setMtime(System.currentTimeMillis());

        record.setPosx(mapBlock.x);
        record.setPosy(mapBlock.y);
        record.setPosz(mapBlock.z);

        record.setX(posx);
        record.setY(posy);
        record.setZ(posz);

        record.insert();
    }

    @Subscribe
    public void onMapBlock(EventBus.MapBlockParsedEvent event){

        MapBlock mapblock = event.mapblock;

        //Clear entries for this mapblock
        ctx
                .deleteFrom(TRAVELNET)
                .where(TRAVELNET.POSX.eq(mapblock.x))
                .and(TRAVELNET.POSY.eq(mapblock.y))
                .and(TRAVELNET.POSZ.eq(mapblock.z))
                .execute();

        if (mapblock.mapping.containsValue(BLOCK_NAME)){

            for (int x=0; x<16; x++){
                for (int y=0; y<16; y++){
                    for (int z=0; z<16; z++){
                        Optional<String> node = mapblock.getNode(x, y, z);

                        if (node.isPresent() && node.get().equals(BLOCK_NAME)){
                                register(mapblock, x,y,z);
                        }
                    }
                }
            }

        }

    }

}
