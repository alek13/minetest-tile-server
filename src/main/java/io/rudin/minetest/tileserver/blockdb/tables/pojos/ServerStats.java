/*
 * This file is generated by jOOQ.
*/
package io.rudin.minetest.tileserver.blockdb.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class ServerStats implements Serializable {

    private static final long serialVersionUID = -1184102491;

    private String key;
    private String value;

    public ServerStats() {}

    public ServerStats(ServerStats value) {
        this.key = value.key;
        this.value = value.value;
    }

    public ServerStats(
        String key,
        String value
    ) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ServerStats (");

        sb.append(key);
        sb.append(", ").append(value);

        sb.append(")");
        return sb.toString();
    }
}