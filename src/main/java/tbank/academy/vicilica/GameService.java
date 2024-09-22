package tbank.academy.vicilica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameService {

    private final List<String> words;
    private StringBuilder guessedWord;

    public GameService(String filename) {
        words = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = bufferedReader.readLine()) != null) {
                words.add(word.toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    public String getRandomWord(){
        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));
        guessedWord = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            guessedWord.append("*");
        }
        return word;
    }

    public String getGuessedWord() {
        return guessedWord.toString();
    }
    public boolean guesLetter(Character letter, String word) {
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            char lowercaseCh = Character.toLowerCase(letter);
            if (word.charAt(i) == lowercaseCh) {
                guessedWord.setCharAt(i,lowercaseCh);
                found = true;
            }
        }
        return found;
    }

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

    public char getValidLetter(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("Enter letter: ");
            input = scanner.nextLine().trim(); // Обрезаем пробелы по краям

            if (input.isEmpty()) {  // Если строка пустая
                System.out.println("Invalid input. Please enter a letter.");
            } else if (input.length() != 1) { // Проверка на количество введённых символов
                System.out.println("Please enter only one letter.");
            } else {
                return input.charAt(0);  // Если введён один символ, возвращаем его
            }
        }
    }

    public boolean askPlayAgain(Scanner scanner, Set<String> usedLetters) {
        System.out.println("Do you want play again?");
        System.out.println("[N]ew game or [E]xit");
        String answer = scanner.nextLine().toLowerCase();
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

    public void playGame(Scanner scanner, String randomWord, Set<String> usedLetters, int maxTries) {
        System.out.println("You have " + maxTries + " tries");
        Prints state = Prints.ZERO;
        int count = 0;
        int countPrint = 0;
        while (count != maxTries) {
            if (getGuessedWord().equals(randomWord)) {
                System.out.println("Congratulations YOU ARE WIN!!!");
                System.out.println("It was " + getGuessedWord());
                System.out.println();
                break;
            }
            System.out.println(state);
            System.out.println("Current state of the word: " + getGuessedWord());
            System.out.println("Used letters: " + String.join(", ", usedLetters));
            char letter = getValidLetter(scanner);

            if (guesLetter(letter, randomWord)) {
                System.out.println("Letter " + letter + " is found!");
            } else {
                if (usedLetters.contains(Character.toString(letter))) {
                    continue;
                }
                count++;
                if (maxTries == 7) {
                    countPrint = count;
                }
                if (maxTries == 5){
                    if (countPrint >= 3) {
                        countPrint += 2;
                    }
                    else {
                        countPrint++;
                    }
                }
                if (maxTries == 3 ) {
                    if(countPrint == 0){
                        countPrint +=3;
                    } else {
                        countPrint +=2;
                    }
                }
                state = getCountOfMistakes(countPrint);
            }
            usedLetters.add(Character.toString(letter).toLowerCase());
        }

        if (!getGuessedWord().equals(randomWord)) {
            System.out.println(Prints.SEVEN);
            System.out.println("You lost, it was: " + randomWord.toLowerCase() + ", try again");
        }
    }
}
