package more_abstraction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileWordSource implements WordSource {

    public final Map<String, String> words;

    public FileWordSource(String filename) {
        words = new HashMap<>();
        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String word = parts[0].trim().toLowerCase();
                    String hint = parts[1].trim();
                    words.put(word, hint);
                }
            }
        } catch (
                IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    /**
     * Метод для загадывания слова
     */
    @Override
    public String getRandomWord() {
        Random random = new Random();
        List<String> keys = new ArrayList<>(words.keySet());
        return keys.get(random.nextInt(keys.size()));
    }

    /**
     * Метод для передачи мапы слов и подсказок
     */
    public Map<String, String> getWords() {
        return this.words;
    }
}