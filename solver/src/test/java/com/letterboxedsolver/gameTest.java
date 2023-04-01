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

    }

}
