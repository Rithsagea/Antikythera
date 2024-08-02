package moe.lita.antikythera;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import moe.lita.antikythera.data.Board;
import moe.lita.antikythera.data.Game;
import moe.lita.antikythera.data.Randomizer;

@State(Scope.Benchmark)
public class BoardBenchmarks {

    private Board board;

    @Setup(Level.Trial)
    public void setup() {
        board = new Board(Game.BOARD_WIDTH, Game.BOARD_HEIGHT)
                .set(3, 0, true)
                .set(0, 3, true)
                .set(1, 2, true)
                .set(3, 2, true);

        Randomizer rand = new Randomizer((int) System.currentTimeMillis());
        for (int i = 0; i < 30; i++) {
            int x = (int) (rand.nextFloat() * (board.width));
            int y = (int) (rand.nextFloat() * (board.height));
            board.set(x, y, true);
        }
    }

    @Benchmark
    public void cloneBenchmark(Blackhole bh) {
        bh.consume(new Board(board));
    }
}
