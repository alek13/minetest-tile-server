/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb.tables;


import io.rudin.minetest.tileserver.blockdb.DefaultSchema;
import io.rudin.minetest.tileserver.blockdb.Indexes;
import io.rudin.minetest.tileserver.blockdb.Keys;
import io.rudin.minetest.tileserver.blockdb.tables.records.PlayerMetadataRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class PlayerMetadata extends TableImpl<PlayerMetadataRecord> {

    private static final long serialVersionUID = 2000008773;

    /**
     * The reference instance of <code>player_metadata</code>
     */
    public static final PlayerMetadata PLAYER_METADATA = new PlayerMetadata();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PlayerMetadataRecord> getRecordType() {
        return PlayerMetadataRecord.class;
    }

    /**
     * The column <code>player_metadata.player</code>.
     */
    public final TableField<PlayerMetadataRecord, String> PLAYER = createField("player", org.jooq.impl.SQLDataType.VARCHAR(60).nullable(false), this, "");

    /**
     * The column <code>player_metadata.attr</code>.
     */
    public final TableField<PlayerMetadataRecord, String> ATTR = createField("attr", org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>player_metadata.value</code>.
     */
    public final TableField<PlayerMetadataRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>player_metadata</code> table reference
     */
    public PlayerMetadata() {
        this(DSL.name("player_metadata"), null);
    }

    /**
     * Create an aliased <code>player_metadata</code> table reference
     */
    public PlayerMetadata(String alias) {
        this(DSL.name(alias), PLAYER_METADATA);
    }

    /**
     * Create an aliased <code>player_metadata</code> table reference
     */
    public PlayerMetadata(Name alias) {
        this(alias, PLAYER_METADATA);
    }

    private PlayerMetadata(Name alias, Table<PlayerMetadataRecord> aliased) {
        this(alias, aliased, null);
    }

    private PlayerMetadata(Name alias, Table<PlayerMetadataRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PLAYER_METADATA_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PlayerMetadataRecord> getPrimaryKey() {
        return Keys.PLAYER_METADATA_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PlayerMetadataRecord>> getKeys() {
        return Arrays.<UniqueKey<PlayerMetadataRecord>>asList(Keys.PLAYER_METADATA_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<PlayerMetadataRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PlayerMetadataRecord, ?>>asList(Keys.PLAYER_METADATA__PLAYER_METADATA_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerMetadata as(String alias) {
        return new PlayerMetadata(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerMetadata as(Name alias) {
        return new PlayerMetadata(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PlayerMetadata rename(String name) {
        return new PlayerMetadata(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PlayerMetadata rename(Name name) {
        return new PlayerMetadata(name, null);
    }
}
