/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb;


import io.rudin.minetest.tileserver.blockdb.tables.Blocks;
import io.rudin.minetest.tileserver.blockdb.tables.FlywaySchemaHistory;
import io.rudin.minetest.tileserver.blockdb.tables.Missions;
import io.rudin.minetest.tileserver.blockdb.tables.Player;
import io.rudin.minetest.tileserver.blockdb.tables.PlayerInventories;
import io.rudin.minetest.tileserver.blockdb.tables.PlayerInventoryItems;
import io.rudin.minetest.tileserver.blockdb.tables.PlayerMetadata;
import io.rudin.minetest.tileserver.blockdb.tables.Poi;
import io.rudin.minetest.tileserver.blockdb.tables.Travelnet;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index BLOCKS_PKEY = Indexes0.BLOCKS_PKEY;
    public static final Index BLOCKS_TIME = Indexes0.BLOCKS_TIME;
    public static final Index FLYWAY_SCHEMA_HISTORY_PK = Indexes0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final Index FLYWAY_SCHEMA_HISTORY_S_IDX = Indexes0.FLYWAY_SCHEMA_HISTORY_S_IDX;
    public static final Index MISSIONS_MAPBLOCK = Indexes0.MISSIONS_MAPBLOCK;
    public static final Index PLAYER_PKEY = Indexes0.PLAYER_PKEY;
    public static final Index PLAYER_INVENTORIES_PKEY = Indexes0.PLAYER_INVENTORIES_PKEY;
    public static final Index PLAYER_INVENTORY_ITEMS_PKEY = Indexes0.PLAYER_INVENTORY_ITEMS_PKEY;
    public static final Index PLAYER_METADATA_PKEY = Indexes0.PLAYER_METADATA_PKEY;
    public static final Index POI_MAPBLOCK = Indexes0.POI_MAPBLOCK;
    public static final Index TRAVELNET_MAPBLOCK = Indexes0.TRAVELNET_MAPBLOCK;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index BLOCKS_PKEY = Internal.createIndex("blocks_pkey", Blocks.BLOCKS, new OrderField[] { Blocks.BLOCKS.POSX, Blocks.BLOCKS.POSY, Blocks.BLOCKS.POSZ }, true);
        public static Index BLOCKS_TIME = Internal.createIndex("blocks_time", Blocks.BLOCKS, new OrderField[] { Blocks.BLOCKS.MTIME }, false);
        public static Index FLYWAY_SCHEMA_HISTORY_PK = Internal.createIndex("flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
        public static Index FLYWAY_SCHEMA_HISTORY_S_IDX = Internal.createIndex("flyway_schema_history_s_idx", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SUCCESS }, false);
        public static Index MISSIONS_MAPBLOCK = Internal.createIndex("missions_mapblock", Missions.MISSIONS, new OrderField[] { Missions.MISSIONS.POSX, Missions.MISSIONS.POSY, Missions.MISSIONS.POSZ }, false);
        public static Index PLAYER_PKEY = Internal.createIndex("player_pkey", Player.PLAYER, new OrderField[] { Player.PLAYER.NAME }, true);
        public static Index PLAYER_INVENTORIES_PKEY = Internal.createIndex("player_inventories_pkey", PlayerInventories.PLAYER_INVENTORIES, new OrderField[] { PlayerInventories.PLAYER_INVENTORIES.PLAYER, PlayerInventories.PLAYER_INVENTORIES.INV_ID }, true);
        public static Index PLAYER_INVENTORY_ITEMS_PKEY = Internal.createIndex("player_inventory_items_pkey", PlayerInventoryItems.PLAYER_INVENTORY_ITEMS, new OrderField[] { PlayerInventoryItems.PLAYER_INVENTORY_ITEMS.PLAYER, PlayerInventoryItems.PLAYER_INVENTORY_ITEMS.INV_ID, PlayerInventoryItems.PLAYER_INVENTORY_ITEMS.SLOT_ID }, true);
        public static Index PLAYER_METADATA_PKEY = Internal.createIndex("player_metadata_pkey", PlayerMetadata.PLAYER_METADATA, new OrderField[] { PlayerMetadata.PLAYER_METADATA.PLAYER, PlayerMetadata.PLAYER_METADATA.ATTR }, true);
        public static Index POI_MAPBLOCK = Internal.createIndex("poi_mapblock", Poi.POI, new OrderField[] { Poi.POI.POSX, Poi.POI.POSY, Poi.POI.POSZ }, false);
        public static Index TRAVELNET_MAPBLOCK = Internal.createIndex("travelnet_mapblock", Travelnet.TRAVELNET, new OrderField[] { Travelnet.TRAVELNET.POSX, Travelnet.TRAVELNET.POSY, Travelnet.TRAVELNET.POSZ }, false);
    }
}
