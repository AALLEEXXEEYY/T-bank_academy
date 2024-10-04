package more_abstraction;

import java.util.*;

public abstract class GameImpl implements Game {
    private StringBuilder guessedWord = new StringBuilder();
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
        ClassLoader classLoader = ConsoleGame.class.getClassLoader();
        return Objects.requireNonNull(classLoader.getResource(filename)).getFile();
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
     * Метод для получения подсказки
     * @param guessWord загаданное слово
     */
    @Override
    public void getHint(String guessWord) {
        System.out.println("Hint: " + words.get(guessWord));
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

    public boolean winGame() {
        if (getGuessedWord().equals(word)) {
            System.out.println("Congratulations YOU ARE WIN!!!");
            System.out.println("It was " + getGuessedWord());
            System.out.println();
            return true;
        }
        return false;
    }

    public boolean loseGame() {
        if(!getGuessedWord().equals(word)){
            System.out.println(Prints.SEVEN);
            System.out.println("You lost, it was: " + word.toLowerCase() + ", try again");
            return true;
        }
        return false;
    }

}