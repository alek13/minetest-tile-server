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
public class Missions implements Serializable {

    private static final long serialVersionUID = -942932051;

    private Integer id;
    private String  name;
    private String  description;
    private Integer time;
    private String  owner;
    private Integer x;
    private Integer y;
    private Integer z;
    private Integer posx;
    private Integer posy;
    private Integer posz;
    private Long    mtime;

    public Missions() {}

    public Missions(Missions value) {
        this.id = value.id;
        this.name = value.name;
        this.description = value.description;
        this.time = value.time;
        this.owner = value.owner;
        this.x = value.x;
        this.y = value.y;
        this.z = value.z;
        this.posx = value.posx;
        this.posy = value.posy;
        this.posz = value.posz;
        this.mtime = value.mtime;
    }

    public Missions(
        Integer id,
        String  name,
        String  description,
        Integer time,
        String  owner,
        Integer x,
        Integer y,
        Integer z,
        Integer posx,
        Integer posy,
        Integer posz,
        Long    mtime
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.z = z;
        this.posx = posx;
        this.posy = posy;
        this.posz = posz;
        this.mtime = mtime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return this.z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Integer getPosx() {
        return this.posx;
    }

    public void setPosx(Integer posx) {
        this.posx = posx;
    }

    public Integer getPosy() {
        return this.posy;
    }

    public void setPosy(Integer posy) {
        this.posy = posy;
    }

    public Integer getPosz() {
        return this.posz;
    }

    public void setPosz(Integer posz) {
        this.posz = posz;
    }

    public Long getMtime() {
        return this.mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Missions (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(time);
        sb.append(", ").append(owner);
        sb.append(", ").append(x);
        sb.append(", ").append(y);
        sb.append(", ").append(z);
        sb.append(", ").append(posx);
        sb.append(", ").append(posy);
        sb.append(", ").append(posz);
        sb.append(", ").append(mtime);

        sb.append(")");
        return sb.toString();
    }
}
