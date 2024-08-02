package moe.lita.antikythera;

import moe.lita.antikythera.data.Game;
import moe.lita.antikythera.data.Randomizer;
import moe.lita.antikythera.search.Candidate;

public class ManualBenchmark {

    public void run() {
        int iterations = 100000;
        for (int i = 0; i < iterations; i++)
            testCandidates();

        System.out.println("Average Candidates: " + (double) counter / iterations);
        System.out.println("Average Time: " + (double) timer / iterations / 1000000d + "ms");
        System.out.println("Iterations: " + iterations);
    }

    public int counter = 0;
    public long timer = 0;

    public void testCandidates() {
        Game game = Game.builder().build();

        Randomizer rand = new Randomizer((int) System.nanoTime());
        for (int i = 0; i < 30; i++) {
            int x = (int) (rand.nextFloat() * (game.board.width));
            int y = (int) (rand.nextFloat() * (game.board.height - 3));
            game.board.set(x, y, true);
        }
        long start = 0, end = 0;

        start = System.nanoTime();
        var candidates = Candidate.findCandidates(game);
        end = System.nanoTime();
        timer += end - start;

        counter += candidates.size();
    }
}
