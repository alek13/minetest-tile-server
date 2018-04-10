/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb;


import io.rudin.minetest.tileserver.blockdb.tables.Blocks;
import io.rudin.minetest.tileserver.blockdb.tables.Player;

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
    public static final Index PLAYER_PKEY = Indexes0.PLAYER_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index BLOCKS_PKEY = Internal.createIndex("blocks_pkey", Blocks.BLOCKS, new OrderField[] { Blocks.BLOCKS.POSX, Blocks.BLOCKS.POSY, Blocks.BLOCKS.POSZ }, true);
        public static Index PLAYER_PKEY = Internal.createIndex("player_pkey", Player.PLAYER, new OrderField[] { Player.PLAYER.NAME }, true);
    }
}
