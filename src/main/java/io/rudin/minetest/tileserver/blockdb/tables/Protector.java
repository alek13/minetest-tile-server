/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb.tables;


import io.rudin.minetest.tileserver.blockdb.DefaultSchema;
import io.rudin.minetest.tileserver.blockdb.Indexes;
import io.rudin.minetest.tileserver.blockdb.Keys;
import io.rudin.minetest.tileserver.blockdb.tables.records.ProtectorRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class Protector extends TableImpl<ProtectorRecord> {

    private static final long serialVersionUID = 606264464;

    /**
     * The reference instance of <code>protector</code>
     */
    public static final Protector PROTECTOR = new Protector();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProtectorRecord> getRecordType() {
        return ProtectorRecord.class;
    }

    /**
     * The column <code>protector.id</code>.
     */
    public final TableField<ProtectorRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('protector_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>protector.owner</code>.
     */
    public final TableField<ProtectorRecord, String> OWNER = createField("owner", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>protector.x</code>.
     */
    public final TableField<ProtectorRecord, Integer> X = createField("x", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>protector.y</code>.
     */
    public final TableField<ProtectorRecord, Integer> Y = createField("y", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>protector.z</code>.
     */
    public final TableField<ProtectorRecord, Integer> Z = createField("z", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>protector.posx</code>.
     */
    public final TableField<ProtectorRecord, Integer> POSX = createField("posx", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>protector.posy</code>.
     */
    public final TableField<ProtectorRecord, Integer> POSY = createField("posy", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>protector.posz</code>.
     */
    public final TableField<ProtectorRecord, Integer> POSZ = createField("posz", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>protector.mtime</code>.
     */
    public final TableField<ProtectorRecord, Long> MTIME = createField("mtime", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>protector</code> table reference
     */
    public Protector() {
        this(DSL.name("protector"), null);
    }

    /**
     * Create an aliased <code>protector</code> table reference
     */
    public Protector(String alias) {
        this(DSL.name(alias), PROTECTOR);
    }

    /**
     * Create an aliased <code>protector</code> table reference
     */
    public Protector(Name alias) {
        this(alias, PROTECTOR);
    }

    private Protector(Name alias, Table<ProtectorRecord> aliased) {
        this(alias, aliased, null);
    }

    private Protector(Name alias, Table<ProtectorRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.PROTECTOR_MAPBLOCK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ProtectorRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PROTECTOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Protector as(String alias) {
        return new Protector(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Protector as(Name alias) {
        return new Protector(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Protector rename(String name) {
        return new Protector(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Protector rename(Name name) {
        return new Protector(name, null);
    }
}
