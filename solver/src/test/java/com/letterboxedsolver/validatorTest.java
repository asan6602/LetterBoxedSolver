package com.letterboxedsolver;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class validatorTest {
    private validator v = new validator();
    
    @Test
    public void testValidateInput() {
        boolean expected = true;
        boolean actual = v.validateInput("ABC,def,ghi,jkl");
        assertEquals(expected, actual);
    }

    @Test
    public void testValidateInpuformatfalse() {
        boolean expected = false;
        boolean actual = v.validateInput("ABC,def/ghi,jkl");
        assertEquals(expected, actual);
    }

    @Test
    public void testValidateInpuformatBig() {
        boolean expected = false;
        boolean actual = v.validateInput("ABC,def,ghi,jklmo");
        assertEquals(expected, actual);
    }

    @Test
    public void testValidateInputNonLetter() {
        boolean expected = false;
        boolean actual = v.validateInput("AB4,def,ghi,jkl");
        assertEquals(expected, actual);
    }

    @Test
    public void testValidateInputDuplicateLetter() {
        boolean expected = false;
        boolean actual = v.validateInput("ABB,def,ghi,jkl");
        assertEquals(expected, actual);
    }

    @Test
    public void testConvertInput() {
        ArrayList<ArrayList<Character>> expected = new ArrayList<>();

        ArrayList<Character> row1 = new ArrayList<>();
        row1.add('a');
        row1.add('b');
        row1.add('c');
        expected.add(row1);

        ArrayList<Character> row2 = new ArrayList<>();
        row2.add('d');
        row2.add('e');
        row2.add('f');
        expected.add(row2);

        ArrayList<Character> row3 = new ArrayList<>();
        row3.add('g');
        row3.add('h');
        row3.add('i');
        expected.add(row3);

        ArrayList<Character> row4 = new ArrayList<>();
        row4.add('j');
        row4.add('k');
        row4.add('l');
        expected.add(row4);
        
        ArrayList<ArrayList<Character>> actual = v.convertInput("ABC,def,ghi,jkl");
        assertEquals(expected, actual);
    }
}
