package tbank.academy.vicilica;

import java.util.Scanner;
import java.util.Set;

public interface Game {
    public String choiceValidTheme(Scanner scanner);
    public String getFilenameForTheme(String theme);
    public String choiceLevel(Scanner scanner);
    public boolean guesLetter(Character letter, String word);
    public void hideWord();
    public String getGuessedWord();
    public char getValidLetter(Scanner scanner, String guessWord);
    public void getHint(String guessWord);
    public void playGame(Scanner scanner, String guessWord, Set<String> usedLetters, int maxTries);
    public Prints getCountOfMistakes(int count);
    public boolean askPlayAgain(Scanner scanner, Set<String> usedLetters);
}