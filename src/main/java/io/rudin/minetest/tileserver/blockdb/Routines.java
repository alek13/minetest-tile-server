/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb;


import io.rudin.minetest.tileserver.blockdb.routines.OnBlocksChange;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Field;


/**
 * Convenience access to all stored procedures and functions in 
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    public static Object onBlocksChange(Configuration configuration) {
        OnBlocksChange f = new OnBlocksChange();

        f.execute(configuration);
        return f.getReturnValue();
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    public static Field<Object> onBlocksChange() {
        OnBlocksChange f = new OnBlocksChange();

        return f.asField();
    }
}