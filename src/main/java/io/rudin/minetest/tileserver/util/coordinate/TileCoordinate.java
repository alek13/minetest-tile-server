package io.rudin.minetest.tileserver.util.coordinate;

public class TileCoordinate {
    public TileCoordinate(int x, int y, int zoom){
        this.x = x;
        this.y = y;
        this.zoom = zoom;
    }

    public final int x, y, zoom;


}
