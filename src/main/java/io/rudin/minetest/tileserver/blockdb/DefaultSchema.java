/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb;


import io.rudin.minetest.tileserver.blockdb.tables.Blocks;
import io.rudin.minetest.tileserver.blockdb.tables.Chat;
import io.rudin.minetest.tileserver.blockdb.tables.Player;
import io.rudin.minetest.tileserver.blockdb.tables.PlayerMetadata;
import io.rudin.minetest.tileserver.blockdb.tables.TileserverTiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 229146681;

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>blocks</code>.
     */
    public final Blocks BLOCKS = io.rudin.minetest.tileserver.blockdb.tables.Blocks.BLOCKS;

    /**
     * The table <code>chat</code>.
     */
    public final Chat CHAT = io.rudin.minetest.tileserver.blockdb.tables.Chat.CHAT;

    /**
     * The table <code>player</code>.
     */
    public final Player PLAYER = io.rudin.minetest.tileserver.blockdb.tables.Player.PLAYER;

    /**
     * The table <code>player_metadata</code>.
     */
    public final PlayerMetadata PLAYER_METADATA = io.rudin.minetest.tileserver.blockdb.tables.PlayerMetadata.PLAYER_METADATA;

    /**
     * The table <code>tileserver_tiles</code>.
     */
    public final TileserverTiles TILESERVER_TILES = io.rudin.minetest.tileserver.blockdb.tables.TileserverTiles.TILESERVER_TILES;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Blocks.BLOCKS,
            Chat.CHAT,
            Player.PLAYER,
            PlayerMetadata.PLAYER_METADATA,
            TileserverTiles.TILESERVER_TILES);
    }
}
