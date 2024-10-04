package tbank.academy.vicilica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Map;

class FileWordSourceTest {

    @Test
    public void testSuccessfulFileReading() throws IOException {
        // arrange
        String testFileName = "testFile.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(testFileName))) {
            writer.println("apple, A fruit");
            writer.println("banana, A yellow fruit");
        }

        // act
        FileWordSource fileWordSource = new FileWordSource(testFileName);
        Map<String, String> words = fileWordSource.getWords();

        // assert
        assertEquals(2, words.size());
        assertEquals("A fruit", words.get("apple"));
        assertEquals("A yellow fruit", words.get("banana"));

        new File(testFileName).delete();
    }

    @Test
    public void testEmptyFile() throws IOException {
        // arrange
        String testFileName = "emptyFile.txt";
        new File(testFileName).createNewFile();

        // act
        FileWordSource fileWordSource = new FileWordSource(testFileName);
        Map<String, String> words = fileWordSource.getWords();

        // assert
        assertTrue(words.isEmpty(), "Words map should be empty for an empty file");

        new File(testFileName).delete();
    }

    @Test
    public void testIncorrectlyFormattedLines() throws IOException {
        // arrange
        String testFileName = "incorrectFormatFile.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(testFileName))) {
            writer.println("apple");
            writer.println("banana, A yellow fruit");
        }

        // act
        FileWordSource fileWordSource = new FileWordSource(testFileName);
        Map<String, String> words = fileWordSource.getWords();

        // assert
        assertEquals(1, words.size(), "Only correctly formatted lines should be processed");
        assertEquals("A yellow fruit", words.get("banana"));
        assertNull(words.get("apple"), "Incorrectly formatted lines should be ignored");

        // clean up
        new File(testFileName).delete();
    }

    @Test
    public void testFileNotFound() {
        // arrange
        String testFileName = "nonExistentFile.txt";

        // act и assert
        assertDoesNotThrow(() -> {
            new FileWordSource(testFileName);
        });
    }
}

