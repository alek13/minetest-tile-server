/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb;


import io.rudin.minetest.tileserver.blockdb.tables.Blocks;
import io.rudin.minetest.tileserver.blockdb.tables.Chat;
import io.rudin.minetest.tileserver.blockdb.tables.FlywaySchemaHistory;
import io.rudin.minetest.tileserver.blockdb.tables.Missions;
import io.rudin.minetest.tileserver.blockdb.tables.Player;
import io.rudin.minetest.tileserver.blockdb.tables.PlayerInventories;
import io.rudin.minetest.tileserver.blockdb.tables.PlayerInventoryItems;
import io.rudin.minetest.tileserver.blockdb.tables.PlayerMetadata;
import io.rudin.minetest.tileserver.blockdb.tables.Poi;
import io.rudin.minetest.tileserver.blockdb.tables.Protector;
import io.rudin.minetest.tileserver.blockdb.tables.Shop;
import io.rudin.minetest.tileserver.blockdb.tables.Trainline;
import io.rudin.minetest.tileserver.blockdb.tables.Travelnet;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in 
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>blocks</code>.
     */
    public static final Blocks BLOCKS = io.rudin.minetest.tileserver.blockdb.tables.Blocks.BLOCKS;

    /**
     * The table <code>chat</code>.
     */
    public static final Chat CHAT = io.rudin.minetest.tileserver.blockdb.tables.Chat.CHAT;

    /**
     * The table <code>flyway_schema_history</code>.
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = io.rudin.minetest.tileserver.blockdb.tables.FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>missions</code>.
     */
    public static final Missions MISSIONS = io.rudin.minetest.tileserver.blockdb.tables.Missions.MISSIONS;

    /**
     * The table <code>player</code>.
     */
    public static final Player PLAYER = io.rudin.minetest.tileserver.blockdb.tables.Player.PLAYER;

    /**
     * The table <code>player_inventories</code>.
     */
    public static final PlayerInventories PLAYER_INVENTORIES = io.rudin.minetest.tileserver.blockdb.tables.PlayerInventories.PLAYER_INVENTORIES;

    /**
     * The table <code>player_inventory_items</code>.
     */
    public static final PlayerInventoryItems PLAYER_INVENTORY_ITEMS = io.rudin.minetest.tileserver.blockdb.tables.PlayerInventoryItems.PLAYER_INVENTORY_ITEMS;

    /**
     * The table <code>player_metadata</code>.
     */
    public static final PlayerMetadata PLAYER_METADATA = io.rudin.minetest.tileserver.blockdb.tables.PlayerMetadata.PLAYER_METADATA;

    /**
     * The table <code>poi</code>.
     */
    public static final Poi POI = io.rudin.minetest.tileserver.blockdb.tables.Poi.POI;

    /**
     * The table <code>protector</code>.
     */
    public static final Protector PROTECTOR = io.rudin.minetest.tileserver.blockdb.tables.Protector.PROTECTOR;

    /**
     * The table <code>shop</code>.
     */
    public static final Shop SHOP = io.rudin.minetest.tileserver.blockdb.tables.Shop.SHOP;

    /**
     * The table <code>trainline</code>.
     */
    public static final Trainline TRAINLINE = io.rudin.minetest.tileserver.blockdb.tables.Trainline.TRAINLINE;

    /**
     * The table <code>travelnet</code>.
     */
    public static final Travelnet TRAVELNET = io.rudin.minetest.tileserver.blockdb.tables.Travelnet.TRAVELNET;
}
