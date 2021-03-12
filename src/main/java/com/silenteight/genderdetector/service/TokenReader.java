package com.silenteight.genderdetector.service;

public interface TokenReader {
    boolean isInFemaleTokenList(String name);
    boolean isInMaleTokenList(String name);
    boolean isInTokenList(String fileName, String name);
}
