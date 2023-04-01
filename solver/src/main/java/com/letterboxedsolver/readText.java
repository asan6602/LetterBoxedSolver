package com.letterboxedsolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class readText {

    /**
     * Reads words from a .txt file to use in the game.
     * @param path path of the .txt file
     * @return HashMap sorted by first word.
     */
    public HashMap<Character, ArrayList<String>> read(String path) {
        HashMap<Character, ArrayList<String>> result = new HashMap<>();
        try {

            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while(line != null) {
                
                line = line.toLowerCase();

                if(line.length() < 3) {
                    line = br.readLine();
                    continue;
                }

                //check if line only contains letters
                if(!line.matches("[a-zA-Z]+")) {
                    line = br.readLine();
                    continue;
                }

                //check if line does not have back to back letters
                boolean doubleLetter = false;
                for (int i = 0; i < line.length() - 1; i++) {
                    if (line.charAt(i) == line.charAt(i + 1)) {
                        doubleLetter = true;
                    }
                }

                if(!doubleLetter) {
                    char key = line.charAt(0);

                    //computeIFAbsent returns the new or old value associated with the specified key
                    result.computeIfAbsent(key, k -> new ArrayList<>()).add(line);
                }

                line = br.readLine();
            }

            br.close();
            fr.close();

        }
        catch(IOException e) {
            System.out.print("File Not Found");
        }

        return result;
    }
}
