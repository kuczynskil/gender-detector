package com.silenteight.genderdetector.algorithm;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TokenReader_v1 implements TokenReader {

    public boolean isInFemaleTokenList(String name) {
        name = name.toUpperCase();
        return isInTokenList("female_names.txt", name);
    }

    public boolean isInMaleTokenList(String name) {
        name = name.toUpperCase();
        return isInTokenList("male_names.txt", name);
    }

    public boolean isInTokenList(String fileName, String name) {
        try (InputStream is = TokenReader_v1.class.getResourceAsStream(fileName);
             BufferedReader tokens = new BufferedReader(new InputStreamReader(is))) {
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
