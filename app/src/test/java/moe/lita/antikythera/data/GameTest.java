package moe.lita.antikythera.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void testGameEquality() {
        Game game1 = Game.builder().build();
        Game game2 = game1.clone();

        assertEquals(game1, game2);
        assertEquals(game1.hashCode(), game2.hashCode());

        game1.rotateCcw();
        game1.rotateCcw();
        game1.dasLeft();
        game1.softDrop();

        game2.rotateCw();
        game2.rotateCw();
        game2.softDrop();
        game2.dasLeft();

        assertEquals(game1, game2);
        assertEquals(game1.hashCode(), game2.hashCode());
    }
}
