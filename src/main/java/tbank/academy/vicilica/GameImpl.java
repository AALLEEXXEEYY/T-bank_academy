package tbank.academy.vicilica;

import java.util.*;

public class GameImpl implements Game {
    private StringBuilder guessedWord;
    private Map<String, String> words;
    private String word;

    /**
     * Метод сетит значения полей
     * @param words мапа
     * @param word загаданное слово
     */
    public void setWords(Map<String, String> words, String word) {
        this.words = words;
        this.word = word;
    }

    public GameImpl() {
        this.guessedWord = new StringBuilder();
    }

    /**
     * Метод для выбоа темы
     * @param scanner сканер для ввода
     */
    @Override
    public String choiceValidTheme(Scanner scanner) {
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

    /**
     * Метод для назначения имени файла и загрузки его
     * @param theme тема
     */
    @Override
    public String getFilenameForTheme(String theme) {
        String filename = null;
        switch (theme) {
            case "animals":
                filename = "animals.txt";
                break;
            case "fruits":
                filename = "fruits.txt";
                break;
            case "cities":
                filename = "cities.txt";
                break;
            default:
                return null;
        }
        ClassLoader classLoader = ConsoleGameRealization.class.getClassLoader();
        return Objects.requireNonNull(classLoader.getResource(filename)).getFile();
    }

    /**
     * Метод для выбора уровня
     * @param scanner сканер для ввода
     */
    @Override
    public String choiceLevel(Scanner scanner) {
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

    /**
     *  Метод проверяет есть ли буква в слове
     * @param letter Буква которую ввел пользователь
     * @param word Загаданное слово
     */
    @Override
    public boolean guesLetter(Character letter, String word) {
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            char lowercaseCh = Character.toLowerCase(letter);
            if (word.charAt(i) == lowercaseCh) {
                guessedWord.setCharAt(i, lowercaseCh);
                found = true;
            }
        }
        return found;
    }

    /**
     * Метод для шифрования слова
     */
    @Override
    public void hideWord() {
        guessedWord = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            guessedWord.append("*");
        }
    }

    /**
     * Метод для получения зашифрованного слова
     */
    @Override
    public String getGuessedWord() {
        return guessedWord.toString();
    }

    /**
     * Метод валидации введенный букв
     * @param scanner сканер для ввода
     * @param guessWord загаданное слово
     */
    @Override
    public char getValidLetter(Scanner scanner, String guessWord) {
        String input;
        while (true) {
            System.out.print("Enter letter: ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {  // Если строка пустая
                System.out.println("Invalid input. Please enter a letter.");
            } else if (input.equalsIgnoreCase("help")) {
                getHint(guessWord);
            } else if (input.length() != 1) {
                System.out.println("Please enter only one letter.");
            } else {
                return input.charAt(0);
            }
        }
    }

    /**
     * Метод для получения подсказки
     * @param guessWord загаданное слово
     */
    @Override
    public void getHint(String guessWord) {
        System.out.println("Hint: " + words.get(guessWord));
    }

    /**
     *  Главный игровой метод
     * @param scanner сканер для ввода
     * @param guessWord загаданное слово
     * @param usedLetters Множество использованных букв
     * @param maxTries Максимальное число попыток
     */
    @Override
    public void playGame(Scanner scanner, String guessWord, Set<String> usedLetters, int maxTries) {
        hideWord();
        System.out.println("You have " + maxTries + " tries");
        Prints state = Prints.ZERO;
        int count = 0;
        int countPrint = 0;
        while (count != maxTries) {
            if (getGuessedWord().equals(guessWord)) {
                System.out.println("Congratulations YOU ARE WIN!!!");
                System.out.println("It was " + getGuessedWord());
                System.out.println();
                break;
            }
            System.out.println(state);
            System.out.println("Current state of the word: " + getGuessedWord());
            System.out.println("Used letters: " + String.join(", ", usedLetters));
            System.out.println("If it hard for you and need help enter 'help'");
            char letter = getValidLetter(scanner, guessWord);

            if (guesLetter(letter, guessWord)) {
                System.out.println("Letter " + letter + " is found!");
            } else {
                if (usedLetters.contains(Character.toString(letter))) {
                    continue;
                }
                count++;
                if (maxTries == 7) {
                    countPrint = count;
                }
                if (maxTries == 5) {
                    if (countPrint >= 3) {
                        countPrint += 2;
                    } else {
                        countPrint++;
                    }
                }
                if (maxTries == 3) {
                    if (countPrint == 0) {
                        countPrint += 3;
                    } else {
                        countPrint += 2;
                    }
                }
                state = getCountOfMistakes(countPrint);
            }
            usedLetters.add(Character.toString(letter).toLowerCase());
        }

        if (!getGuessedWord().equals(guessWord)) {
            System.out.println(Prints.SEVEN);
            System.out.println("You lost, it was: " + guessWord.toLowerCase() + ", try again");
        }
    }

    /**
     *  Метод для вывода колличества ошибок
     * @param count колличество ошибок
     */
    @Override
    public Prints getCountOfMistakes(int count) {
        Prints state = null;
        switch (count) {
            case 1:
                state = Prints.ONE;
                break;
            case 2:
                state = Prints.TWO;
                break;
            case 3:
                state = Prints.THREE;
                break;
            case 4:
                state = Prints.FOUR;
                break;
            case 5:
                state = Prints.FIVE;
                break;
            case 6:
                state = Prints.SIX;
                break;
            case 7:
                state = Prints.SEVEN;
                break;
            default:
                state = null;
                break;
        }
        return state;
    }

    /**
     * Метод для повторной игры
     * @param scanner сканер для ввода
     * @param usedLetters Множество использованных букв
     */
    @Override
    public boolean askPlayAgain(Scanner scanner, Set<String> usedLetters) {
        System.out.println("Do you want play again?");
        System.out.println("[N]ew game or [E]xit");
        String answer = scanner.nextLine().toLowerCase().trim();
        if (answer.equals("n")) {
            usedLetters.clear();
            return true;
        } else if (answer.equals("e")) {
            System.out.println("Bye-Bye");
            return false;
        } else {
            System.out.println("I am don't understand you, please repeat");
            return askPlayAgain(scanner, usedLetters);
        }
    }
}