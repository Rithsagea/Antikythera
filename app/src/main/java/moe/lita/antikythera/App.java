package moe.lita.antikythera;

import java.util.Scanner;

import moe.lita.antikythera.data.Game;
import moe.lita.antikythera.data.Randomizer;
import moe.lita.antikythera.search.Candidate;

public class App {

    public static void main(String[] args) throws Exception {
        System.in.read();
        var app = new App();
        int iterations = 100000;
        for (int i = 0; i < iterations; i++) {
            app.testCandidates();
        }
        System.out.println("Average Candidates: " + (double) app.counter / iterations);
        System.out.println("Average Time: " + (double) app.timer / iterations / 1000000d + "ms");
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

    public static void runGame() {
        Game game = Game.builder().build();
        final Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println(game);
            System.out.print("Input: ");
            String in = scanner.nextLine();
            System.out.println();
            switch (in) {
                case "z" -> game.rotateCcw();
                case "x" -> game.rotateCw();
                case "c" -> game.hold();
                case "j" -> game.tapLeft();
                case "J" -> game.dasLeft();
                case "l" -> game.tapRight();
                case "L" -> game.dasRight();
                case "k" -> game.softDrop();
                case "i" -> game.hardDrop();
                case "f" -> {
                    running = false;
                }
            }
        }

        scanner.close();
    }
}
