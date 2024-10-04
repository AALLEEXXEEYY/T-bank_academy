package tbank.academy.vicilica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameImplTest {

    private GameImpl gameImpl;
    private Field guessedWordField;
    private Field wordField;

    private void setGuessedWord(String guessedWord) throws IllegalAccessException {
        guessedWordField.set(gameImpl, new StringBuilder(guessedWord));
    }

    private String getGuessedWord() throws IllegalAccessException {
        return guessedWordField.get(gameImpl).toString();
    }

    private void setWord(String word) throws IllegalAccessException {
        wordField.set(gameImpl, word);
    }
    @BeforeEach
    public void setUp() throws NoSuchFieldException {
        gameImpl = new GameImpl();
        guessedWordField = GameImpl.class.getDeclaredField("guessedWord");
        guessedWordField.setAccessible(true);
        wordField = GameImpl.class.getDeclaredField("word");
        wordField.setAccessible(true);
    }

    @Test
    public void testWordsAreMaskedCorrectly() {
        // arrange
        List<String> testWords = Arrays.asList("apple", "banana", "cherry");

        for (String word : testWords) {
            StringBuilder guessedWord = new StringBuilder();

            // act
            for (int i = 0; i < word.length(); i++) {
                guessedWord.append('*');
            }

            // assert
            assertEquals(word.length(), guessedWord.length());
            assertTrue(guessedWord.toString().chars().allMatch(ch -> ch == '*'));
        }
    }

    @Test
    public void testGuesLetter_FoundOnce() throws IllegalAccessException {
        // arrange
        String word = "apple";
        Character letter = 'a';
        setGuessedWord("*****");

        // act
        boolean result = gameImpl.guesLetter(letter, word);

        // assert
        assertTrue(result, "Letter 'a' should be found in 'apple'");
        assertEquals("a****", getGuessedWord());
    }

    @Test
    public void testGuesLetter_NotFound() throws IllegalAccessException {
        // arrange
        String word = "apple";
        Character letter = 'z';
        setGuessedWord("*****");

        // act
        boolean result = gameImpl.guesLetter(letter, word);

        // assert
        assertFalse(result);
        assertEquals("*****", getGuessedWord());
    }

    @Test
    public void testGuesLetter_FoundMultipleTimes() throws IllegalAccessException {
        // arrange
        String word = "banana";
        Character letter = 'a';
        setGuessedWord("******");

        // act
        boolean result = gameImpl.guesLetter(letter, word);

        // assert
        assertTrue(result);
        assertEquals("*a*a*a", getGuessedWord());
    }

    @Test
    public void testChoiceValidTheme_ValidInputFirstTry() {
        // arrange
        String userInput = "animals\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(in);
        GameImpl spyGameImpl = spy(gameImpl);

        // mock the behavior of getFilenameForTheme
        doReturn("animals.txt").when(spyGameImpl).getFilenameForTheme("animals");

        // act
        String result = spyGameImpl.choiceValidTheme(scanner);

        // assert
        assertEquals("animals.txt", result);
    }

    @Test
    public void testChoiceValidTheme_InvalidThenValidInput() {
        // arrange
        String userInput = "invalid_theme\nfruits\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(in);
        GameImpl spyGameImpl = spy(gameImpl);

        doReturn(null).when(spyGameImpl).getFilenameForTheme("invalid_theme");
        doReturn("fruits.txt").when(spyGameImpl).getFilenameForTheme("fruits");

        // act
        String result = spyGameImpl.choiceValidTheme(scanner);

        // assert
        assertEquals("fruits.txt", result);
    }

    @Test
    public void testChoiceValidTheme_MultipleValidInputs() {
        // arrange
        String userInput = "cities\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(in);
        GameImpl spyGameImpl = spy(gameImpl);

        doReturn("cities.txt").when(spyGameImpl).getFilenameForTheme("cities");

        // act
        String result = spyGameImpl.choiceValidTheme(scanner);

        // assert
        assertEquals("cities.txt", result);
    }

    @Test
    public void testHideWord_HidesCorrectly() throws IllegalAccessException {
        // arrange
        setWord("apple");

        // act
        gameImpl.hideWord();

        // assert
        assertEquals("*****", getGuessedWord(), "The word should be hidden as a string of asterisks");
    }

    @Test
    public void testHideWord_EmptyWord() throws IllegalAccessException {
        // arrange
        setWord("");

        // act
        gameImpl.hideWord();

        // assert
        assertEquals("", getGuessedWord(), "Empty word should result in an empty hidden word");
    }

}
