package moe.lita.antikythera;

import moe.lita.antikythera.data.Board;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class BoardBenchmarks {

    private Board board;

    @Setup(Level.Trial)
    public void setup() {
        board = new Board(5, 5)
            .set(3, 0, true)
            .set(0, 3, true)
            .set(1, 2, true)
            .set(3, 2, true);
    }

    @Benchmark
    public void cloneBenchmark(Blackhole bh) {
        bh.consume(new Board(board));
    }
}
