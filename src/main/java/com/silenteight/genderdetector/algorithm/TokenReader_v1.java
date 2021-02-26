package com.silenteight.genderdetector.algorithm;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class TokenReader_v1 implements TokenReader{


    public boolean isInFemaleTokenList(String name) {
        name = name.toUpperCase();
        return isInTokenList("female_names.txt", name);
    }

    public boolean isInMaleTokenList(String name) {
        name = name.toUpperCase();
        return isInTokenList("male_names.txt", name);
    }

    public boolean isInTokenList(String fileName, String name) {
        try (BufferedReader tokens = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while (null != (line = tokens.readLine())) {
                if (line.equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
