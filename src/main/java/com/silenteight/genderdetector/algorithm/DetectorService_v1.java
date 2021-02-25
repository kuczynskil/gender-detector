package com.silenteight.genderdetector.algorithm;

import org.springframework.stereotype.Service;

@Service
public class DetectorService_v1 implements DetectorService {

    private final TokenReader reader;

    public DetectorService_v1(TokenReader reader) {
        this.reader = reader;
    }

    @Override
    public String detectGenderByFirstTokenOfName(String name) {
        return "FEMALE";
    }

    @Override
    public String detectGenderByMajorityRule(String name) {
        return "FEMALE";
    }

}
