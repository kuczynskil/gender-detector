package com.silenteight.genderdetector.algorithm;

import com.silenteight.genderdetector.service.TokenReader_v1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenReaderV1Test {

    private final TokenReader_v1 reader = new TokenReader_v1();

    @Test
    void should_return_true_because_name_lists_contain_given_Strings() {
        assertTrue(reader.isInFemaleTokenList("maria"));
        assertTrue(reader.isInFemaleTokenList("MAriA"));
        assertTrue(reader.isInFemaleTokenList("MariA"));
        assertTrue(reader.isInFemaleTokenList("Gabriela"));
        assertTrue(reader.isInFemaleTokenList("gertruda"));

        assertTrue(reader.isInMaleTokenList("Michał"));
        assertTrue(reader.isInMaleTokenList("Adam"));
        assertTrue(reader.isInMaleTokenList("adam"));
        assertTrue(reader.isInMaleTokenList("wojciech"));
        assertTrue(reader.isInMaleTokenList("łukasz"));
    }

    @Test
    void should_return_false_because_name_lists_does_not_contain_given_Strings() {
        assertFalse(reader.isInFemaleTokenList(""));
        assertFalse(reader.isInFemaleTokenList("M4ria"));
        assertFalse(reader.isInFemaleTokenList("m0nika"));
        assertFalse(reader.isInFemaleTokenList("angelikaa"));
        assertFalse(reader.isInFemaleTokenList("ange likaa"));
        assertFalse(reader.isInFemaleTokenList("michał"));

        assertFalse(reader.isInMaleTokenList(""));
        assertFalse(reader.isInMaleTokenList("agnieszka"));
        assertFalse(reader.isInMaleTokenList("Łókasz"));
        assertFalse(reader.isInMaleTokenList("michau"));
        assertFalse(reader.isInMaleTokenList("abcxyz123"));
    }
}