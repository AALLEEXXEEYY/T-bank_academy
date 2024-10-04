package more_abstraction;

import java.util.*;

public class ConsoleGame extends GameImpl {

    private Set<String> usedLetters;
    private Prints state;
    private int count;
    private int maxTries;
    public boolean validationTheme(String theme) {
        Set<String> themeSet = new HashSet<>(Arrays.asList("animals", "fruits", "cities"));
        if (themeSet.contains(theme)) {
            return true;
        }
        return false;
    }

    public boolean validationLevel(String level) {
        Set<String> levelList = new HashSet<>(Arrays.asList("junior", "middle", "senior"));
        if (levelList.contains(level)) {
            return true;
        }
        return false;
    }

    @Override
    public char getValidLetter(Scanner scanner, String guessWord) {
        return 0;
    }

    public boolean getValidLetter(String inputChar, String randomWord) {
        if (inputChar.isEmpty()) {
            System.out.println("Invalid input. Please enter a letter.");
        } else if (inputChar.equalsIgnoreCase("help")) {
            getHint(randomWord);
        } else if (inputChar.length() != 1) {
            System.out.println("Please enter only one letter.");
        } else {
            return true;
        }
        return false;
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

//    /**
//     * Метод для повторной игры
//     * @param answer ответ пользователя
//     * @param usedLetters Множество использованных букв
//     */
//    @Override
//    public Boolean wantPlayAgain(String answer, Set<String> usedLetters) {
//        Boolean flag = null;
//        if (answer.equals("n")) {
//            usedLetters.clear();
//            return false;
//        } else if (answer.equals("e")) {
//            System.out.println("Bye-Bye");
//            return true;
//        } else {
//            System.out.println("I am don't understand you, please repeat");
//        }
//        return flag;
//    }

    public void startGame(int maxTries, String randomWord) {
        this.maxTries = maxTries;
        this.usedLetters = new TreeSet<>();
        this.state = Prints.ZERO;
        this.count = 0;
        hideWord();

        while (count < maxTries) {
            if (winGame()) {
                System.out.println("Congratulations, you won!");
                break;
            }

            displayGameState();

            String input = getValidInput(randomWord);
            if (processLetter(input, randomWord)) {
                System.out.println("Letter " + input + " is found!");
            } else {
                updateStateOnWrongGuess(input);
            }
        }

        if (loseGame()) {
            System.out.println("Sorry, you lost! The word was: " + randomWord);
        }
    }


    public void displayGameState() {
        System.out.println(state);
        System.out.println("Current state of the word: " + getGuessedWord());
        System.out.println("Used letters: " + String.join(", ", usedLetters));
    }


    public String getValidInput(String randomWord) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("If it hard for you and need help enter 'help'");
            System.out.print("Enter letter: ");
            input = scanner.nextLine().trim();
            if (getValidLetter(input, randomWord)) {
                break;
            }
        }
        return input;
    }

    public boolean processLetter(String input, String randomWord) {
        if (guesLetter(input.charAt(0), randomWord)) {
            usedLetters.add(input.toLowerCase());
            return true;

        } else {

            usedLetters.add(input.toLowerCase());

            return false;
        }
    }

    public void updateStateOnWrongGuess(String input) {
        if (!usedLetters.contains(input.toLowerCase())) {
            count++;
            state = getCountOfMistakes(count);
            usedLetters.add(input.toLowerCase());
        }
    }

}