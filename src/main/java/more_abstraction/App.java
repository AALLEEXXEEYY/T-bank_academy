package more_abstraction;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class App extends ConsoleGame {
    public static void main(String[] args) {
        System.out.println("Welcome dear user, let`s play!");
        Scanner scanner = new Scanner(System.in);
        Set<String> usedLetters = new TreeSet<>();
        ConsoleGame realization = new ConsoleGame();
        String theme;
        String level;
        while (true) {

            while (true) {
                System.out.println("Choose a theme (animals, fruits, cities):");
                theme = scanner.nextLine().toLowerCase().trim();
                if (realization.validationTheme(theme)) {
                    break;
                }
                System.out.println("Invalid theme. Please choose again.");
            }


            String filename = realization.getFilenameForTheme(theme);
            FileWordSource fileWordSource = new FileWordSource(filename);


            while (true) {
                System.out.println("Choose level (junior, middle, senior):");
                level = scanner.nextLine().toLowerCase().trim();
                if (realization.validationLevel(level)) {
                    break;
                }
                System.out.println("Invalid level. Please choose again.");
            }
            String randomWord = fileWordSource.getRandomWord();
            realization.setWords(fileWordSource.getWords(), randomWord);
            realization.hideWord();
//            switch (level) {
//                case "junior":
//                    realization.startGame(7, randomWord);
//                    break;
//                case "middle":
//                    realization.startGame(5, randomWord);
//                    break;
//                case "senior":
//                    realization.startGame(3, randomWord);
//                    break;
//            }
            String input;
            switch (level) {
                case "junior":
                    int maxTries = 7;

                    System.out.println("You have " + maxTries + " tries");
                    Prints state = Prints.ZERO;
                    int count = 0;
                    int countPrint = 0;
                    while (count != maxTries) {
                        if (realization.winGame()){
                            break;
                        }
                        System.out.println(state);
                        System.out.println("Current state of the word: " + realization.getGuessedWord());
                        System.out.println("Used letters: " + String.join(", ", usedLetters));
                        System.out.println("If it hard for you and need help enter 'help'");


                        while (true) {
                            System.out.print("Enter letter: ");
                            input = scanner.nextLine().trim();
                            if (realization.getValidLetter(input, randomWord)){
                                break;
                            }
                        }


                        if (realization.guesLetter(input.charAt(0), randomWord)) {
                            System.out.println("Letter " + input + " is found!");
                        } else {
                            if (usedLetters.contains(Character.toString(input.charAt(0)))) {
                                continue;
                            }
                            count++;

                            if (maxTries == 7) {
                                countPrint = count;
                            }
                            state = realization.getCountOfMistakes(countPrint);
                        }
                        usedLetters.add(Character.toString(input.charAt(0)).toLowerCase());
                    }

                    if (realization.loseGame()) {
                        break;
                    }

                    break;
                case "middle":
                    maxTries = 5;

                    System.out.println("You have " + maxTries + " tries");
                    state = Prints.ZERO;
                    count = 0;
                    countPrint = 0;
                    while (count != maxTries) {
                        if (realization.winGame()){
                            break;
                        }
                        System.out.println(state);
                        System.out.println("Current state of the word: " + realization.getGuessedWord());
                        System.out.println("Used letters: " + String.join(", ", usedLetters));
                        System.out.println("If it hard for you and need help enter 'help'");


                        while (true) {
                            System.out.print("Enter letter: ");
                            input = scanner.nextLine().trim();
                            if (realization.getValidLetter(input, randomWord)){
                                break;
                            }
                        }


                        if (realization.guesLetter(input.charAt(0), randomWord)) {
                            System.out.println("Letter " + input + " is found!");
                        } else {
                            if (usedLetters.contains(Character.toString(input.charAt(0)))) {
                                continue;
                            }
                            count++;

                            if (maxTries == 5) {
                                if (countPrint >= 3) {
                                    countPrint += 2;
                                } else {
                                    countPrint++;
                                }
                            }
                            state = realization.getCountOfMistakes(countPrint);
                        }
                        usedLetters.add(Character.toString(input.charAt(0)).toLowerCase());
                    }

                    if (realization.loseGame()) {
                        break;
                    }
                    break;
                case "senior":
                    maxTries = 3;

                    System.out.println("You have " + maxTries + " tries");
                    state = Prints.ZERO;
                    count = 0;
                    countPrint = 0;
                    while (count != maxTries) {
                        if (realization.winGame()){
                            break;
                        }
                        System.out.println(state);
                        System.out.println("Current state of the word: " + realization.getGuessedWord());
                        System.out.println("Used letters: " + String.join(", ", usedLetters));
                        System.out.println("If it hard for you and need help enter 'help'");


                        while (true) {
                            System.out.print("Enter letter: ");
                            input = scanner.nextLine().trim();
                            if (realization.getValidLetter(input, randomWord)){
                                break;
                            }
                        }


                        if (realization.guesLetter(input.charAt(0), randomWord)) {
                            System.out.println("Letter " + input + " is found!");
                        } else {
                            if (usedLetters.contains(Character.toString(input.charAt(0)))) {
                                continue;
                            }
                            count++;

                            if (maxTries == 3) {
                                if (countPrint == 0) {
                                    countPrint += 3;
                                } else {
                                    countPrint += 2;
                                }
                            }
                            state = realization.getCountOfMistakes(countPrint);
                        }
                        usedLetters.add(Character.toString(input.charAt(0)).toLowerCase());
                    }

                    if (realization.loseGame()) {
                        break;
                    }

                    break;
            }

            boolean playGameAgain;
            while (true) {
                System.out.println("Do you want play again?");
                System.out.println("[n]ew game or [e]xit");
                String answer = scanner.nextLine().toLowerCase().trim();
                if (answer.equals("n")) {
                    playGameAgain =  true;
                    usedLetters.clear();
                    break;
                } else if (answer.equals("e")) {
                    System.out.println("Bye-Bye");
                    playGameAgain =  false;
                    break;
                } else {
                    System.out.println("I am don't understand you, please repeat");
                }
            }

            if (!playGameAgain){
                break;
            }

        }
    }
}
