package moe.lita.antikythera;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import moe.lita.antikythera.data.Game;
import moe.lita.antikythera.data.Randomizer;
import moe.lita.antikythera.search.Candidate;

@State(Scope.Benchmark)
public class CandidateBenchmarks {

    private Game game;

    @Setup(Level.Trial)
    public void setup() {
        game = Game.builder().build();

        Randomizer rand = new Randomizer((int) System.currentTimeMillis());
        for (int i = 0; i < 30; i++) {
            int x = (int) (rand.nextFloat() * (game.board.width));
            int y = (int) (rand.nextFloat() * (game.board.height - 3));
            game.board.set(x, y, true);
        }
    }

    @Benchmark
    public void candidateBenchmark(Blackhole bh) {
        bh.consume(Candidate.findCandidates(game));
    }
}
