package com.silenteight.genderdetector.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetectorService_v1Test {

    private final TokenReader_v1 reader = new TokenReader_v1();
    private final DetectorService_v1 detector = new DetectorService_v1(reader);
    private final String FEMALE = "FEMALE";
    private final String MALE = "MALE";
    private final String INCONCLUSIVE = "INCONCLUSIVE";

    @Test
    void returns_FEMALE_if_first_token_of_name_is_in_female_token_list() {

        assertEquals(FEMALE, detector.detectGenderByFirstTokenOfName("Maria"));
        assertEquals(FEMALE, detector.detectGenderByFirstTokenOfName("Maria Monika"));
        assertEquals(FEMALE, detector.detectGenderByFirstTokenOfName("Gabriela"));

        assertNotEquals(FEMALE, detector.detectGenderByFirstTokenOfName("Jan Maria Rokita"));
        assertNotEquals(FEMALE, detector.detectGenderByFirstTokenOfName("Andrzej Agnieszka Agata"));
        assertNotEquals(FEMALE, detector.detectGenderByFirstTokenOfName("Bogdan    1223"));
    }

    @Test
    void returns_MALE_if_first_token_of_name_is_in_male_token_list() {

        assertEquals(MALE, detector.detectGenderByFirstTokenOfName("Jan"));
        assertEquals(MALE, detector.detectGenderByFirstTokenOfName("Jan Maria Rokita"));
        assertEquals(MALE, detector.detectGenderByFirstTokenOfName("wOjciech"));

        assertNotEquals(MALE, detector.detectGenderByFirstTokenOfName("Anna"));
        assertNotEquals(MALE, detector.detectGenderByFirstTokenOfName("Anna Jan Marian"));
        assertNotEquals(MALE, detector.detectGenderByFirstTokenOfName("Wiktoria  admin1234"));
    }

    @Test
    void returns_FEMALE_if_majority_of_names_are_in_female_token_list() {

        assertEquals(FEMALE, detector.detectGenderByMajorityRule("Maria"));
        assertEquals(FEMALE, detector.detectGenderByMajorityRule("Maria Monika"));
        assertEquals(FEMALE, detector.detectGenderByMajorityRule("Maria Monika Wojciech"));
        assertEquals(FEMALE, detector.detectGenderByMajorityRule("Anna Katarzyna Aleksandra Wojciech Adam"));

        assertNotEquals(FEMALE, detector.detectGenderByMajorityRule("Maria Maria Jan Jan Jan"));
        assertNotEquals(FEMALE, detector.detectGenderByMajorityRule("Jan Anna Wojciech"));
        assertNotEquals(FEMALE, detector.detectGenderByMajorityRule("Admin User Krzysztof Jaroslaw"));
    }

    @Test
    void returns_MALE_if_majority_of_names_are_in_male_token_list() {

        assertEquals(MALE, detector.detectGenderByMajorityRule("Jan Marek"));
        assertEquals(MALE, detector.detectGenderByMajorityRule("Marcin"));
        assertEquals(MALE, detector.detectGenderByMajorityRule("Marcin Łukasz Anna Zuzanna Michał"));
        assertEquals(MALE, detector.detectGenderByMajorityRule("Gertruda Zbigniew Włodzimierz"));

        assertNotEquals(MALE, detector.detectGenderByMajorityRule("Maria"));
        assertNotEquals(MALE, detector.detectGenderByMajorityRule("Maria Monika"));
        assertNotEquals(MALE, detector.detectGenderByMajorityRule("    Maria Monika Wojciech   "));
    }

    @Test
    void returns_INCONCLUSIVE_if_the_input_String_contains_the_same_number_of_female_and_male_names() {

        assertEquals(INCONCLUSIVE, detector.detectGenderByMajorityRule("Jan Anna"));
        assertEquals(INCONCLUSIVE, detector.detectGenderByMajorityRule("aaa bbb"));
        assertEquals(INCONCLUSIVE, detector.detectGenderByMajorityRule("admin user"));
        assertEquals(INCONCLUSIVE, detector.detectGenderByMajorityRule("Marcin xyz Monika    "));
        assertEquals(INCONCLUSIVE, detector.detectGenderByMajorityRule("   Adam Andrzej Anna Maria   "));
    }

    @Test
    void returns_INCONCLUSIVE_if_passed_String_is_empty_or_is_longer_than_100_characters() {

        assertEquals(INCONCLUSIVE, detector.detectGenderByFirstTokenOfName(""));
        assertEquals(INCONCLUSIVE, detector.detectGenderByFirstTokenOfName("Piotr Monika Grzegorz some other words, " +
                "where sum of the characters(including whitespaces) is bigger than 100"));

        assertEquals(INCONCLUSIVE, detector.detectGenderByMajorityRule(""));
        assertEquals(INCONCLUSIVE, detector.detectGenderByMajorityRule("Piotr Monika Grzegorz some other words, " +
                "where sum of the characters(including whitespaces) is bigger than 100"));
    }
}