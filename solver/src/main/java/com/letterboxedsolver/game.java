package com.letterboxedsolver;

import java.util.ArrayList;
import java.util.HashMap;

public class game {
    HashMap<Character, ArrayList<String>> fromTxt;
    readText readText = new readText();
    validator validator = new validator();

    public game(String txtFilePath) {
        this.fromTxt = readText.read(txtFilePath);
    }

    /**
     * finds two word solutions that fits the puzzle (made up of letters within the problem, do not have consecutive letters from the same row)
     * @param input string in the format xxx,xxx,xxx where x is a unique character
     * @return an array of two word combinations that solve the game
     */
    public ArrayList<String[]> playGame(String input) {
        ArrayList<String[]> result = new ArrayList<>();
        HashMap<Character, ArrayList<String>> puzzlewords = new HashMap<>();

        ArrayList<ArrayList<Character>> rows = validator.convertInput(input);
    
        if(rows == null) {
            System.out.println("Input is invalid");
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

        //result = solutions(puzzlewords, characterList);

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
        words.forEach((key, value) -> {
            for(String s: value) {
                boolean validWord = true;
                
                //iterate through the characters of the string
                for(int i =0; i < s.length() - 1; i++) {
                    ArrayList<Character> row = new ArrayList<>();
                    
                    //check which row contains the letter
                    for(ArrayList<Character> r: rows) {
                        if(r.contains(s.charAt(i))) {
                            row = r;
                        }
                    }

                    //if the next character is within the row, then the word is not valid
                    if(row.contains(s.charAt(i + 1))) {
                        validWord = false;
                        break;
                    }
                }
                
                if(validWord) {
                    result.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
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

        return result;
    }
}
