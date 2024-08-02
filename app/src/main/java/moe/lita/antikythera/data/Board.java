package moe.lita.antikythera.data;

import java.util.BitSet;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Board {
    /*
     * Bottom left corner is (0, 0)
     * Top right corner is (width - 1, height - 1)
     */

    private final BitSet data;
    public final int width;
    public final int height;

    public Board(int width, int height) {
        data = new BitSet(width * height);
        this.width = width;
        this.height = height;
    }

    public Board(Board board) {
        this.data = (BitSet) board.data.clone();
        this.width = board.width;
        this.height = board.height;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private int indexFor(int x, int y) {
        return y * width + x;
    }

    public boolean get(int x, int y) {
        if (isValid(x, y))
            return data.get(indexFor(x, y));
        return true;
    }

    public Board set(int x, int y, boolean val) {
        if (isValid(x, y))
            data.set(indexFor(x, y), val);
        return this;
    }

    public Board place(Location location, Tetromino tetromino) {
        for (int[] block : tetromino.getData()[location.rotation])
            set(block[0] + location.x, block[1] + location.y, true);
        return this;
    }

    public boolean check(Location location, Tetromino tetromino) {
        for (int[] block : tetromino.getData()[location.rotation])
            if (get(block[0] + location.x, block[1] + location.y))
                return false;
        return true;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                res.append(get(x, y) ? "x" : ".");
            }
            res.append("\n");
        }

        return res.toString();
    }

    // TODO should probably rename this to copy or something; clone/Cloneable are
    // pretty strange
    public Board clone() {
        return new Board(this);
    }
}
