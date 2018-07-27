/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in 
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>missions_id_seq</code>
     */
    public static final Sequence<Integer> MISSIONS_ID_SEQ = new SequenceImpl<Integer>("missions_id_seq", DefaultSchema.DEFAULT_SCHEMA, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>poi_id_seq</code>
     */
    public static final Sequence<Integer> POI_ID_SEQ = new SequenceImpl<Integer>("poi_id_seq", DefaultSchema.DEFAULT_SCHEMA, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>travelnet_id_seq</code>
     */
    public static final Sequence<Integer> TRAVELNET_ID_SEQ = new SequenceImpl<Integer>("travelnet_id_seq", DefaultSchema.DEFAULT_SCHEMA, org.jooq.impl.SQLDataType.INTEGER.nullable(false));
}
