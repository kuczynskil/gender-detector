package com.silenteight.genderdetector.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TokenReader {


    protected boolean isInFemaleTokenList(String name) {
        name = name.toUpperCase();

        try (BufferedReader femaleTokens = new BufferedReader(new FileReader("female_names.txt"))) {
            String line = "";
            while (null != (line = femaleTokens.readLine())) {
                if (line.equals(name)) {
                    return true;
                }
            }
        } catch (IOException f) {
            f.printStackTrace();
        }
        return false;
    }

    protected boolean isInMaleTokenList(String name) {
        name = name.toUpperCase();

        try (BufferedReader maleTokens = new BufferedReader(new FileReader("male_names.txt"))) {
            String line = "";
            while (null != (line = maleTokens.readLine())) {
                if (line.equals(name)) {
                    return true;
                }
            }
        } catch (IOException f) {
            f.printStackTrace();
        }
        return false;
    }

}
