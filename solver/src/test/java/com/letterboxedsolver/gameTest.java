package com.letterboxedsolver;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class gameTest {
    private readText rt = new readText();
    private String path = "data\\wordsTest2.txt";
    private game g = new game(path);
    private ArrayList<Character> characterList = new ArrayList<>();
    private String letters = "abcdefghijkl";
    ArrayList<ArrayList<Character>> rows = new ArrayList<>();

    @Before
	public void setUp() throws Exception {
        for(int i =0; i < letters.length(); i++) {
            this.characterList.add(letters.charAt(i));
        }
        ArrayList<Character> row1 = new ArrayList<>(Arrays.asList('a','b','c'));
        ArrayList<Character> row2 = new ArrayList<>(Arrays.asList('d','e','f'));
        ArrayList<Character> row3 = new ArrayList<>(Arrays.asList('g','h','i'));
        ArrayList<Character> row4 = new ArrayList<>(Arrays.asList('j','k','l'));

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
	}

    @Test
    public void testFindWordsInProblem() {
        HashMap<Character, ArrayList<String>> expected = new HashMap<>();
        ArrayList<String> words = new ArrayList<>();
        words.add("ale");
        words.add("abel");
        words.add("adelgidae");
        expected.put('a', words);

        HashMap<Character, ArrayList<String>> actual = g.findWordsInProblem(characterList);

        assertEquals(expected, actual);
        
    }

    @Test
    public void testCheckRows() {
        HashMap<Character, ArrayList<String>> wordsInput = new HashMap<>();
        ArrayList<String> words1 = new ArrayList<>();
        words1.add("ale");
        words1.add("abel");
        words1.add("adelgidae");
        wordsInput.put('a', words1);

        HashMap<Character, ArrayList<String>> expected = new HashMap<>();
        ArrayList<String> words2 = new ArrayList<>();
        words2.add("ale");
        expected.put('a',words2);

        HashMap<Character, ArrayList<String>> actual = g.checkRows(rows, wordsInput);
        
        assertEquals(expected, actual);

    }

}
