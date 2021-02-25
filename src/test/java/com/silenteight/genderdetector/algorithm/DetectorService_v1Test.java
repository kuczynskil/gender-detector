package com.silenteight.genderdetector.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetectorService_v1Test {

    private final TokenReader reader = new TokenReader();
    private final DetectorService_v1 detector = new DetectorService_v1(reader);

    @Test
    void returns_FEMALE_if_first_token_of_name_is_in_female_token_list() {
        String female = "FEMALE";

        assertEquals(female, detector.detectGenderByFirstTokenOfName("Maria "));
    }

    @Test
    void returns_MALE_if_first_token_of_name_is_in_male_token_list() {
        String male = "MALE";

        assertEquals(male, detector.detectGenderByFirstTokenOfName("Jan"));
    }

    @Test
    void detectGenderByMajorityRule() {
    }
}