package moe.lita.antikythera;

import java.util.Scanner;

import moe.lita.antikythera.data.Game;

public class TestGame {
    public void run() {
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
