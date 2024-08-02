package moe.lita.antikythera.data;

import java.util.List;

import lombok.EqualsAndHashCode;

// from https://github.com/Poyo-SSB/tetrio-bot-docs/blob/master/Piece_RNG.md
@EqualsAndHashCode
public class Randomizer {
    private int seed;

    public Randomizer(int seed) {
        this.seed = seed & Integer.MAX_VALUE;
    }

    public int getSeed() {
        return seed;
    }

    public int next() {
        return seed = (16807 * seed) & Integer.MAX_VALUE;
    }

    public double nextFloat() {
        return (double) (next() - 1) / Integer.MAX_VALUE;
    }

    public <T> void shuffle(List<T> list) {
        for (int i = list.size() - 1; i != 0; i--) {
            final int r = (int) Math.floor(nextFloat() * (i + 1));
            var temp = list.get(i);
            list.set(i, list.get(r));
            list.set(r, temp);
        }
    }

    public Randomizer clone() {
        return new Randomizer(this.seed);
    }
}
