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
public class TileserverBlockDepth implements Serializable {

    private static final long serialVersionUID = 424033598;

    private Integer posx;
    private Integer posz;
    private Integer visibley;

    public TileserverBlockDepth() {}

    public TileserverBlockDepth(TileserverBlockDepth value) {
        this.posx = value.posx;
        this.posz = value.posz;
        this.visibley = value.visibley;
    }

    public TileserverBlockDepth(
        Integer posx,
        Integer posz,
        Integer visibley
    ) {
        this.posx = posx;
        this.posz = posz;
        this.visibley = visibley;
    }

    public Integer getPosx() {
        return this.posx;
    }

    public void setPosx(Integer posx) {
        this.posx = posx;
    }

    public Integer getPosz() {
        return this.posz;
    }

    public void setPosz(Integer posz) {
        this.posz = posz;
    }

    public Integer getVisibley() {
        return this.visibley;
    }

    public void setVisibley(Integer visibley) {
        this.visibley = visibley;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TileserverBlockDepth (");

        sb.append(posx);
        sb.append(", ").append(posz);
        sb.append(", ").append(visibley);

        sb.append(")");
        return sb.toString();
    }
}
