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
}
