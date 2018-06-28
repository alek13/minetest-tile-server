/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb.tables.records;


import io.rudin.minetest.tileserver.blockdb.tables.PlayerInventories;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


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
public class PlayerInventoriesRecord extends UpdatableRecordImpl<PlayerInventoriesRecord> implements Record5<String, Integer, Integer, String, Integer> {

    private static final long serialVersionUID = 1777746625;

    /**
     * Setter for <code>player_inventories.player</code>.
     */
    public void setPlayer(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>player_inventories.player</code>.
     */
    public String getPlayer() {
        return (String) get(0);
    }

    /**
     * Setter for <code>player_inventories.inv_id</code>.
     */
    public void setInvId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>player_inventories.inv_id</code>.
     */
    public Integer getInvId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>player_inventories.inv_width</code>.
     */
    public void setInvWidth(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>player_inventories.inv_width</code>.
     */
    public Integer getInvWidth() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>player_inventories.inv_name</code>.
     */
    public void setInvName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>player_inventories.inv_name</code>.
     */
    public String getInvName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>player_inventories.inv_size</code>.
     */
    public void setInvSize(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>player_inventories.inv_size</code>.
     */
    public Integer getInvSize() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<String, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<String, Integer, Integer, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<String, Integer, Integer, String, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return PlayerInventories.PLAYER_INVENTORIES.PLAYER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return PlayerInventories.PLAYER_INVENTORIES.INV_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return PlayerInventories.PLAYER_INVENTORIES.INV_WIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return PlayerInventories.PLAYER_INVENTORIES.INV_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return PlayerInventories.PLAYER_INVENTORIES.INV_SIZE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getInvId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getInvWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getInvName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getInvSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getInvId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getInvWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getInvName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getInvSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInventoriesRecord value1(String value) {
        setPlayer(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInventoriesRecord value2(Integer value) {
        setInvId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInventoriesRecord value3(Integer value) {
        setInvWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInventoriesRecord value4(String value) {
        setInvName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInventoriesRecord value5(Integer value) {
        setInvSize(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInventoriesRecord values(String value1, Integer value2, Integer value3, String value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlayerInventoriesRecord
     */
    public PlayerInventoriesRecord() {
        super(PlayerInventories.PLAYER_INVENTORIES);
    }

    /**
     * Create a detached, initialised PlayerInventoriesRecord
     */
    public PlayerInventoriesRecord(String player, Integer invId, Integer invWidth, String invName, Integer invSize) {
        super(PlayerInventories.PLAYER_INVENTORIES);

        set(0, player);
        set(1, invId);
        set(2, invWidth);
        set(3, invName);
        set(4, invSize);
    }
}
