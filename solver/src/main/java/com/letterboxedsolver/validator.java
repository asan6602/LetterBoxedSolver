package com.letterboxedsolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class validator {

    /**
     * checks if the input is valid (only letters, no repeating letters, size of 12, correct format)
     * @param input string of unique characters in the format xxx,xxx,xxx 
     * @return boolean that says whether or not the input fits the format or not
     */
    public boolean validateInput(String input) {
        boolean result = false;

        input = input.toLowerCase();

        //check if input is in xxx,xxx,xxx format
        if(!input.matches("[a-zA-Z]{3},[a-zA-Z]{3},[a-zA-Z]{3},[a-zA-Z]{3}")) {
            System.out.println("Input is invalid because it is not in <xxx,xxx,xxx,xxx> format");
            return result;
        }

        //remove all non letters from a string
        String justLetters = input.replaceAll("[^a-zA-Z]", "");

        if (justLetters.length() == 12) {

            //add all characters in the string to a set, sets contain no duplicate elements
            Set<Character> testSet = new HashSet<Character>();
            for (int i = 0; i < justLetters.length(); i++) {
                testSet.add(justLetters.charAt(i));
            }

            //good to go if set is the same size as the letter string
            if(testSet.size() == justLetters.length()) {
                return true;
            }
            else {
                System.out.println("Input is invalid because it contains duplicate elements");
                return result;
            }
            
        }
        System.out.println("Input is invalid because is it the wrong size.");
        return result;
    }

    /**
     * puts the characters into a 2d ArrayList in order to represent rows
     * @param input string of unique characters in the format xxx,xxx,xxx 
     * @return 4 ArrayLists stored in a 2d ArrayList
     */
    public ArrayList<ArrayList<Character>> convertInput(String input) {
        input = input.toLowerCase();

        if(!validateInput(input)) {
            return null;
        }
        
        ArrayList<ArrayList<Character>> result =  new ArrayList<ArrayList<Character>>();

        String[] arrOfStrings = input.split(",");

        for(String s: arrOfStrings) {
            ArrayList<Character> chars = new ArrayList<Character>();
            for(char c: s.toCharArray()) {
                chars.add(c);
            }
            result.add(chars);
        }

        return result;
    }
}
