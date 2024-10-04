package tbank.academy.vicilica;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ConsoleGameRealization {
    public static void main(String[] args) {
        System.out.println("Welcome dear user, let`s play!");
        Scanner scanner = new Scanner(System.in);
        GameImpl game = new GameImpl();
        Set<String> usedLetters = new TreeSet<>();
        while (true) {
            String filename = game.choiceValidTheme(scanner);
            FileWordSource fileWordSource = new FileWordSource(filename);
            String level = game.choiceLevel(scanner);
            String randomWord = fileWordSource.getRandomWord();
            game.setWords(fileWordSource.getWords(), randomWord);
            switch (level) {
                case "junior":
                    game.playGame(scanner, randomWord, usedLetters, 7);
                    break;
                case "middle":
                    game.playGame(scanner, randomWord, usedLetters, 5);
                    break;
                case "senior":
                    game.playGame(scanner, randomWord, usedLetters, 3);
                    break;
            }
            if (!game.askPlayAgain(scanner, usedLetters)) {
                break;
            }
        }
    }
}
