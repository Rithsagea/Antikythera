package moe.lita.antikythera.search;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import moe.lita.antikythera.data.Action;
import moe.lita.antikythera.data.Game;

public class CandidateTest {
    @Test
    public void testCandidateSearch() {
        var game = Game.builder().build();
        var candidates = Candidate.findCandidates(game);
        game.hardDrop();
        assertArrayEquals(candidates.get(game).toArray(), new Action[] { Action.HARD_DROP });
    }
}
