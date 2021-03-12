package com.silenteight.genderdetector.service;

public interface FileReader {
    boolean isInFemaleTokenList(String name);
    boolean isInMaleTokenList(String name);
    boolean isInTokenList(String fileName, String name);
}
