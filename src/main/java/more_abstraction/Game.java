package more_abstraction;

import java.util.Scanner;
import java.util.Set;

public interface Game {
    public boolean validationTheme(String theme);
    public String getFilenameForTheme(String theme);
    public boolean validationLevel(String level);
    public boolean guesLetter(Character letter, String word);
    public void hideWord();
    public String getGuessedWord();
    public char getValidLetter(Scanner scanner, String guessWord);
    public void getHint(String guessWord);
    public void playGame(Scanner scanner, String guessWord, Set<String> usedLetters, int maxTries);
    public Prints getCountOfMistakes(int count);
}