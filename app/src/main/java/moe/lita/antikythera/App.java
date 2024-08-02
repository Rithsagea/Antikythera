package moe.lita.antikythera;

import java.util.ArrayList;
import java.util.Collections;

import moe.lita.antikythera.data.Board;
import moe.lita.antikythera.data.Game;
import moe.lita.antikythera.search.Candidate;
import moe.lita.antikythera.search.Node;

public class App {

    public static double evaluateBoard(Game game) {
        int cover = 0;
        int parity = 0;
        int height = 0;
        Board board = game.board;
        for (int x = 0; x < board.width; x++) {
            int count = 0;
            for (int y = 0; y < board.height; y++) {
                if (board.get(x, y)) {
                    parity += ((x + y) % 2 == 0) ? 1 : -1;

                    cover += count;
                    count = 0;
                    height = Math.max(y, height);
                } else {
                    count++;
                }
            }
        }

        int dependency = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < board.width; x++) {
                if (!board.get(x, y)) {
                    dependency += height - y;
                }
            }
        }

        return Math.log(parity + 1) * 1.0 + cover * 1.0 + Math.log10(dependency + 1) * 1.0;
    }

    public static void main(String[] args) throws Exception {
        Game game = Game.builder().build();
        Node root = new Node(game);
        while (true) {
            System.out.println(root.game);
            System.out.println("PAUSE");
            // System.in.read();
            Thread.sleep(1000);

            var moves = new ArrayList<>(Candidate.findCandidates(game).values());
            for (var actions : moves) {
                Game childGame = root.game.clone();
                actions.forEach(a -> a.callback.apply(childGame));
                Node child = new Node(childGame);
                child.score = evaluateBoard(childGame);
                root.edges.add(child);
            }

            Collections.sort(root.edges, (a, b) -> Double.compare(a.score, b.score));
            root = root.edges.get(0);
        }
    }
}
