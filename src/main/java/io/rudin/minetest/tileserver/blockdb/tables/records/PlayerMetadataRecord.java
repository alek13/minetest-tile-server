/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb.tables.records;


import io.rudin.minetest.tileserver.blockdb.tables.PlayerMetadata;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class PlayerMetadataRecord extends UpdatableRecordImpl<PlayerMetadataRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = -1513420317;

    /**
     * Setter for <code>player_metadata.player</code>.
     */
    public void setPlayer(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>player_metadata.player</code>.
     */
    public String getPlayer() {
        return (String) get(0);
    }

    /**
     * Setter for <code>player_metadata.attr</code>.
     */
    public void setAttr(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>player_metadata.attr</code>.
     */
    public String getAttr() {
        return (String) get(1);
    }

    /**
     * Setter for <code>player_metadata.value</code>.
     */
    public void setValue(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>player_metadata.value</code>.
     */
    public String getValue() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return PlayerMetadata.PLAYER_METADATA.PLAYER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return PlayerMetadata.PLAYER_METADATA.ATTR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return PlayerMetadata.PLAYER_METADATA.VALUE;
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
    public String component2() {
        return getAttr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getValue();
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
    public String value2() {
        return getAttr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerMetadataRecord value1(String value) {
        setPlayer(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerMetadataRecord value2(String value) {
        setAttr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerMetadataRecord value3(String value) {
        setValue(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerMetadataRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlayerMetadataRecord
     */
    public PlayerMetadataRecord() {
        super(PlayerMetadata.PLAYER_METADATA);
    }

    /**
     * Create a detached, initialised PlayerMetadataRecord
     */
    public PlayerMetadataRecord(String player, String attr, String value) {
        super(PlayerMetadata.PLAYER_METADATA);

        set(0, player);
        set(1, attr);
        set(2, value);
    }
}
