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
}
