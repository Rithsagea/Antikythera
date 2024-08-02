package moe.lita.antikythera.search;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import moe.lita.antikythera.data.Action;
import moe.lita.antikythera.data.Game;
import moe.lita.antikythera.data.Randomizer;

public class CandidateTest {
    @Test
    public void testCandidateSearch() {
        var game = new Game.Builder().build();

        Randomizer rand = new Randomizer(1);
        for (int i = 0; i < 30; i++) {
            int x = (int) (rand.nextFloat() * game.board.width);
            int y = (int) (rand.nextFloat() * game.board.height);
            game.board.set(x, y, true);
        }
        long start = System.currentTimeMillis();
        var candidates = Candidate.findCandidates(game);
        long end = System.currentTimeMillis();
        System.out.println(game);
        System.out.println("Candidates: " + candidates.values().size());
        System.out.printf("%dms\n", end - start);

        game.hardDrop();
        assertArrayEquals(candidates.get(game).toArray(), new Action[] { Action.HARD_DROP });
    }
}
