package com.letterboxedsolver;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class readTextTest {

    @Test
    public void testRead() {

        HashMap<Character, ArrayList<String>> expected = new HashMap<>();

        ArrayList<String> r = new ArrayList<>(Arrays.asList("roach"));
        ArrayList<String> w = new ArrayList<>(Arrays.asList("word", "world","wow"));
        ArrayList<String> p = new ArrayList<>(Arrays.asList("potato", "pepsi"));

        expected.put('r', r);
        expected.put('w', w);
        expected.put('p',p);

        readText reader = new readText();
        HashMap<Character, ArrayList<String>> actual = reader.read("data\\wordsTest.txt");      

        assertEquals(expected, actual);
    }

}