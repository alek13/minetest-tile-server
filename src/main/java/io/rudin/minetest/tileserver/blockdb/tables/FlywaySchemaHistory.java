/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb.tables;


import io.rudin.minetest.tileserver.blockdb.DefaultSchema;
import io.rudin.minetest.tileserver.blockdb.Indexes;
import io.rudin.minetest.tileserver.blockdb.Keys;
import io.rudin.minetest.tileserver.blockdb.tables.records.FlywaySchemaHistoryRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class FlywaySchemaHistory extends TableImpl<FlywaySchemaHistoryRecord> {

    private static final long serialVersionUID = -1958796302;

    /**
     * The reference instance of <code>flyway_schema_history</code>
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = new FlywaySchemaHistory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FlywaySchemaHistoryRecord> getRecordType() {
        return FlywaySchemaHistoryRecord.class;
    }

    /**
     * The column <code>flyway_schema_history.installed_rank</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Integer> INSTALLED_RANK = createField("installed_rank", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>flyway_schema_history.version</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> VERSION = createField("version", org.jooq.impl.SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>flyway_schema_history.description</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>flyway_schema_history.type</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>flyway_schema_history.script</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> SCRIPT = createField("script", org.jooq.impl.SQLDataType.VARCHAR(1000).nullable(false), this, "");

    /**
     * The column <code>flyway_schema_history.checksum</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Integer> CHECKSUM = createField("checksum", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>flyway_schema_history.installed_by</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> INSTALLED_BY = createField("installed_by", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>flyway_schema_history.installed_on</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Timestamp> INSTALLED_ON = createField("installed_on", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>flyway_schema_history.execution_time</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Integer> EXECUTION_TIME = createField("execution_time", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>flyway_schema_history.success</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Boolean> SUCCESS = createField("success", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * Create a <code>flyway_schema_history</code> table reference
     */
    public FlywaySchemaHistory() {
        this(DSL.name("flyway_schema_history"), null);
    }

    /**
     * Create an aliased <code>flyway_schema_history</code> table reference
     */
    public FlywaySchemaHistory(String alias) {
        this(DSL.name(alias), FLYWAY_SCHEMA_HISTORY);
    }

    /**
     * Create an aliased <code>flyway_schema_history</code> table reference
     */
    public FlywaySchemaHistory(Name alias) {
        this(alias, FLYWAY_SCHEMA_HISTORY);
    }

    private FlywaySchemaHistory(Name alias, Table<FlywaySchemaHistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private FlywaySchemaHistory(Name alias, Table<FlywaySchemaHistoryRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.FLYWAY_SCHEMA_HISTORY_PK, Indexes.FLYWAY_SCHEMA_HISTORY_S_IDX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<FlywaySchemaHistoryRecord> getPrimaryKey() {
        return Keys.FLYWAY_SCHEMA_HISTORY_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<FlywaySchemaHistoryRecord>> getKeys() {
        return Arrays.<UniqueKey<FlywaySchemaHistoryRecord>>asList(Keys.FLYWAY_SCHEMA_HISTORY_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlywaySchemaHistory as(String alias) {
        return new FlywaySchemaHistory(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlywaySchemaHistory as(Name alias) {
        return new FlywaySchemaHistory(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public FlywaySchemaHistory rename(String name) {
        return new FlywaySchemaHistory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public FlywaySchemaHistory rename(Name name) {
        return new FlywaySchemaHistory(name, null);
    }
}
