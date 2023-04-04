package com.letterboxedsolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class game {
    HashMap<Character, ArrayList<String>> fromTxt;
    readText readText = new readText();
    validator validator = new validator();

    public game(String txtFilePath) {
        this.fromTxt = readText.read(txtFilePath);
    }

    /**
     * finds two word solutions that fits the puzzle (made up of letters within the problem, do not have consecutive letters from the same row)
     * @param input string in the format xxx,xxx,xxx,xxx where x is a unique character
     * @return an arrayList of String[] that are two word combinations that solve the game
     */
    public ArrayList<String[]> playGame(String input) {
        ArrayList<String[]> result = new ArrayList<>();

        HashMap<Character, ArrayList<String>> puzzlewords = new HashMap<>();

        ArrayList<ArrayList<Character>> rows = validator.convertInput(input);
    
        if(rows == null) {
            return null;
        }

        //list of characters, want to use arraylist since it has the contains method
        String justLetters = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        char[] letterArray = justLetters.toCharArray();
        ArrayList<Character> characterList = new ArrayList<>();
        for(char c: letterArray) {
            characterList.add(c);
        }

        puzzlewords = findWordsInProblem(characterList);

        puzzlewords = checkRows(rows, puzzlewords);

        result = solutions(puzzlewords, characterList);

        return result;
    }

    /**
     * helper function that returns words with characters only made up of characters within the problem
     * @param characterList list of characters that make up the characters of the problem
     * @return a Hashmap made up of words only consisting of characters within the problem
     */
    public HashMap<Character, ArrayList<String>> findWordsInProblem(ArrayList<Character> characterList) {
        HashMap<Character, ArrayList<String>> result = new HashMap<>();
        
        for(char k : characterList) {
            char key = k;
            ArrayList<String> words = fromTxt.get(k);
            ArrayList<String> puzzleWords = new ArrayList<>();

            if(words == null) {
                continue;
            }
            
            for(String word: words) {
                boolean isValidWord = true;
                for(int i = 0; i <word.length(); i++) {
                    char c = word.charAt(i);
                        if(!characterList.contains(c)) {
                            isValidWord = false;
                            break;
                        }
                }
                if(isValidWord) {
                    puzzleWords.add(word);
                }
            }

            result.put(key, puzzleWords);

        }

        return result;
    }

    /**
     * helper function that returns words that do not have consecutive letters belonging to the same row.
     * @param rows 2d ArrayList of characters that represent the rows in the game
     * @param words words to parse through
     * @return a Hashmap made up of words that do not have consecurtive letters belonging to the same row
     */
    public HashMap<Character, ArrayList<String>> checkRows(ArrayList<ArrayList<Character>> rows, HashMap<Character, ArrayList<String>> words) {
        HashMap<Character, ArrayList<String>> result = new HashMap<>();

        //iterating through a hashmap, for each and lambda
        words.forEach((key, keyWords) -> {
            for(String word: keyWords) {
                boolean validWord = true;
                
                //iterate through the characters of the string
                for(int i =0; i < word.length() - 1; i++) {
                    ArrayList<Character> row = new ArrayList<>();
                    
                    //check which row contains the letter
                    for(ArrayList<Character> r: rows) {
                        if(r.contains(word.charAt(i))) {
                            row = r;
                        }
                    }

                    //if the next character is within the row, then the word is not valid
                    if(row.contains(word.charAt(i + 1))) {
                        validWord = false;
                        break;
                    }
                }
                
                if(validWord) {
                    result.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
                }
            }
        });
        return result;
    }

    /**
     * gets the two word solutions that solve the game
     * @param puzzleWords words made up of letters within the problem, do not have consecutive letters from the same row
     * @param problemCharacters all the characters tin the initial problem
     * @return an arraylist of String[] with two words that fufill the game
     */
    public ArrayList<String[]> solutions(HashMap<Character, ArrayList<String>> puzzleWords, ArrayList<Character> problemCharacters) {
        ArrayList<String[]> result = new ArrayList<>();

        puzzleWords.forEach((key, keyWords) -> {
            for(String word1: keyWords) {
                char lastLetter = word1.charAt(word1.length()-1);

                ArrayList<String> wordStartingWithLastCharacter = puzzleWords.get(lastLetter);

                if(wordStartingWithLastCharacter != null) {
                    //checking words that beginng with last letter of first word
                    for(String word2: wordStartingWithLastCharacter) {
                        boolean valid = true;
                        String combinedWord = word1+word2;

                        HashSet<Character> combinedList = new HashSet<>();
                        for (char characterCombined : combinedWord.toCharArray()) {
                            combinedList.add(characterCombined);
                        }

                        //check that all problemCharacters are present in the two words
                        if(combinedList.size() != problemCharacters.size()) {
                            valid = false;
                        }
    
                        if (valid) {
                            String[] solution = new String[2];
                            solution[0] = word1;
                            solution[1] = word2;
                            result.add(solution);
                        }
                    }
                }
                   
            }
        });

        return result;
    }

    public static void main(String args[]) {
        game g  = new game("solver\\data\\cleanWords.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter problem in format <xxx,xxx,xxx,xxx>: ");

        String input = scanner.nextLine();

        long start = System.currentTimeMillis();

        ArrayList<String[]> result = g.playGame(input);

        if (result != null) {
            for(String[] combo: result) {
                System.out.println(combo[0] + " " + combo[1]);
            }
    
            System.out.println("There are " + result.size() + " possible solutions");
        }


        long ms = System.currentTimeMillis() - start;
        System.out.println("Time elapsed: " + ms + " ms.");

        scanner.close();

    }
}
