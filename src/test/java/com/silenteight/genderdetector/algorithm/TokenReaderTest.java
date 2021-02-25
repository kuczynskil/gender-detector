package com.silenteight.genderdetector.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenReaderTest {

    private TokenReader reader = new TokenReader();

    @Test
    void returns_true() {
        assertTrue(reader.isInFemaleTokenList("maria"));
    }
}