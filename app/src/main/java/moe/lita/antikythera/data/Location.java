package moe.lita.antikythera.data;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Location {
    public int x;
    public int y;
    public int rotation;

    public Location(int x, int y, int rotation) {
        this.x = x;
        this.y = y;
        this.rotation = Math.floorMod(rotation, 4);
    }

    public Location clone() {
        return new Location(x, y, rotation);
    }
}
