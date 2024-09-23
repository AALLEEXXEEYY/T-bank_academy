package tbank.academy.vicilica;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @Test
    public void testWordsAreMaskedCorrectly() {
        List<String> testWords = Arrays.asList("apple", "banana", "cherry");
        for (String word : testWords) {
            StringBuilder guessedWord = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                guessedWord.append('*');
            }
            assertEquals(word.length(), guessedWord.length());
            assertTrue(guessedWord.toString().chars().allMatch(ch -> ch == '*'));
        }
    }

    @Test
    public void testGuesLetter_FoundOnce() throws NoSuchFieldException, IllegalAccessException {
        GameService gameService = new GameService();
        String word = "apple";
        Character letter = 'a';

        Field guessedWordField = GameService.class.getDeclaredField("guessedWord");
        guessedWordField.setAccessible(true);
        guessedWordField.set(gameService, new StringBuilder("*****"));

        boolean result = gameService.guesLetter(letter, word);
        assertTrue(result, "Letter 'a' should be found in 'apple'");
        assertEquals("a****", gameService.getGuessedWord());
    }

    @Test
    public void testGuesLetter_NotFound() throws NoSuchFieldException, IllegalAccessException {
        GameService gameService = new GameService();
        String word = "apple";
        Character letter = 'z';

        Field guessedWordField = GameService.class.getDeclaredField("guessedWord");
        guessedWordField.setAccessible(true);
        guessedWordField.set(gameService, new StringBuilder("*****"));

        boolean result = gameService.guesLetter(letter, word);
        assertFalse(result);
        assertEquals("*****", gameService.getGuessedWord());
    }

    @Test
    public void testGuesLetter_FoundMultipleTimes() throws NoSuchFieldException, IllegalAccessException {
        GameService gameService = new GameService();
        String word = "banana";
        Character letter = 'a';

        Field guessedWordField = GameService.class.getDeclaredField("guessedWord");
        guessedWordField.setAccessible(true);
        guessedWordField.set(gameService, new StringBuilder("******"));

        boolean result = gameService.guesLetter(letter, word);
        assertTrue(result);
        assertEquals("*a*a*a", gameService.getGuessedWord());
    }
}