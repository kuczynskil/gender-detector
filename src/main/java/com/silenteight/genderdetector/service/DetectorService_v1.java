package com.silenteight.genderdetector.service;

import org.springframework.stereotype.Service;

@Service
public class DetectorService_v1 implements DetectorService {

    private final FileReader_v1 reader;
    private static final String FEMALE = "FEMALE";
    private static final String MALE = "MALE";
    private static final String INCONCLUSIVE = "INCONCLUSIVE";

    public DetectorService_v1(FileReader_v1 reader) {
        this.reader = reader;
    }

    @Override
    public String detectGenderByFirstTokenOfName(String name) {
        if (name.length() == 0 || name.length() > 100) return INCONCLUSIVE;

        name = name.trim();
        name = name.split(" ")[0];
        return getGender(name);
    }

    @Override
    public String detectGenderByMajorityRule(String name) {
        if (name.length() == 0 || name.length() > 100) return INCONCLUSIVE;

        name = name.trim();
        String[] names = name.split(" ");
        int maleTokens = 0;
        int femaleTokens = 0;

        for (String s : names) {
            if (getGender(s).equals(MALE)) maleTokens++;
            else if (getGender(s).equals(FEMALE)) femaleTokens++;
        }
        if (maleTokens == femaleTokens) return INCONCLUSIVE;
        return maleTokens > femaleTokens ? MALE : FEMALE;
    }

    private String getGender(String name) {
        if (reader.isInFemaleTokenList(name)) return FEMALE;
        else if (reader.isInMaleTokenList(name)) return MALE;
        else return INCONCLUSIVE;
    }
}
