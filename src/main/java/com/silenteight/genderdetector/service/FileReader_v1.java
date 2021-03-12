package com.silenteight.genderdetector.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileReader_v1 implements FileReader {

    private static final String RESOURCE_PATH = "src/main/resources/com/silenteight/genderdetector/algorithm/";

    public boolean isInFemaleTokenList(String name) {
        name = name.toUpperCase();
        return isInTokenList(RESOURCE_PATH + "female_names.txt", name);
    }

    public boolean isInMaleTokenList(String name) {
        name = name.toUpperCase();
        return isInTokenList(RESOURCE_PATH + "male_names.txt", name);
    }

    public boolean isInTokenList(String fileName, String name) {
        try (BufferedReader tokens = new BufferedReader(new java.io.FileReader(fileName))) {
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
