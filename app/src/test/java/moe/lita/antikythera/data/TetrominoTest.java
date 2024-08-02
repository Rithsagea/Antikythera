package moe.lita.antikythera.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class TetrominoTest {
    @Test
    void printRotations() {
        for (Tetromino mino : Tetromino.values()) {
            System.out.printf("-=-=- %s -=-=-\n", mino);
            String[][] displays = IntStream.range(0, 4)
                    .mapToObj(i -> new Board(5, 5).place(new Location(2, 2, i), mino))
                    .map(Board::toString)
                    .map(str -> str.split("\n"))
                    .toArray(String[][]::new);

            for (int j = 0; j < 5; j++) {
                System.out.print("|");
                for (int i = 0; i < 4; i++) {
                    System.out.print(displays[i][j]);
                    System.out.print("|");
                }
                System.out.println();
            }
        }
    }

    @Test
    void zSpinTriple() {
        Game game = Game.builder()
                .board(new Board(5, 5)
                        .set(3, 0, true)
                        .set(0, 3, true)
                        .set(1, 2, true)
                        .set(3, 2, true))
                .location(new Location(1, 3, 0))
                .activePiece(Tetromino.Z)
                .build();

        game.rotate(1);
        assertEquals(new Location(1, 1, 1), game.location);
        game.rotate(0);
        assertEquals(new Location(1, 3, 0), game.location);
    }

    @Test
    void tSpinTriple() {
        Game game = Game.builder()
                .board(new Board(5, 5)
                        .set(1, 0, true)
                        .set(1, 2, true)
                        .set(2, 4, true)
                        .set(3, 1, true))
                .location(new Location(1, 3, 0))
                .activePiece(Tetromino.T)
                .build();

        game.rotate(3);
        assertEquals(new Location(2, 1, 3), game.location);
        game.rotate(0);
        assertEquals(new Location(1, 3, 0), game.location);
    }

    @Test
    void iSpinTriple() {
        Game game = Game.builder()
                .board(new Board(5, 5)
                        .set(0, 4, true)
                        .set(1, 2, true)
                        .set(2, 2, true)
                        .set(3, 2, true))
                .location(new Location(1, 3, 0))
                .activePiece(Tetromino.I)
                .build();

        game.rotate(1);
        assertEquals(new Location(0, 2, 1), game.location);
        game.rotate(0);
        assertEquals(new Location(1, 3, 0), game.location);
    }
}
