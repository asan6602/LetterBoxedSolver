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

        return result;
    }

    /**
     * helper function that returns words with characters only made up of characters within the problem
     * @param characterList list of characters that make up the characters of the problem
     * @return a Hashmap made up of words only consisting of characters within the problem
     */
    public HashMap<Character, ArrayList<String>> findWordsInProblem(ArrayList<Character> characterList) {
        HashMap<Character, ArrayList<String>> result = new HashMap<>();

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
