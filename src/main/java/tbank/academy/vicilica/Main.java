package tbank.academy.vicilica;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome dear user, let`s play!");
        Scanner scanner = new Scanner(System.in);
        Set<String> usedLetters = new TreeSet<>();  // Упорядочное множество использованных букв
        while (true) {
            String filename = choiceValidTheme(scanner); // выбор темы
            GameService gameService = new GameService(filename);
            String level = choiceLevel(scanner); // выбор уровня сложности
            String randomWord = gameService.getRandomWord(); // выбор рандомного слова и его кодировка
            switch (level) {
                case "junior":
                    gameService.playGame(scanner, randomWord, usedLetters, 7);
                    break;
                case "middle":
                    gameService.playGame(scanner, randomWord, usedLetters, 5);
                    break;
                case "senior":
                    gameService.playGame(scanner, randomWord, usedLetters, 3);
                    break;
            }
            if (!gameService.askPlayAgain(scanner, usedLetters)) {
                break;
            }
        }
    }

    private static String choiceValidTheme(Scanner scanner) {
        String themeLink;
        while (true) {
            System.out.println("Choose a theme (animals, fruits, cities):");
            String theme = scanner.nextLine().toLowerCase().trim();
            themeLink = getFilenameForTheme(theme);
            if (themeLink != null) {
                break;
            }
            System.out.println("Invalid theme. Please choose again.");
        }
        return themeLink;
    }

    private static String choiceLevel(Scanner scanner) {
        String level;
        while (true) {
            System.out.println("Choose level (junior, middle, senior):");
            Set<String> levelList = new HashSet<>(Arrays.asList("junior", "middle", "senior"));
            String difficulty = scanner.nextLine().toLowerCase().trim();
            if (levelList.contains(difficulty)) {
                level = difficulty;
                break;
            }
            System.out.println("Invalid level. Please choose again.");
        }
        return level;
    }

    private static String getFilenameForTheme(String theme) {
        switch (theme) {
            case "animals":
                return "src\\main\\resources\\animals.txt";
            case "fruits":
                return "src\\main\\resources\\fruits.txt";
            case "cities":
                return "src\\main\\resources\\cities.txt";
            default:
                return null;
        }
    }
}
