package com.silenteight.genderdetector.algorithm;

public interface DetectorService {
    String detectGenderByFirstTokenOfName(String name);
    String detectGenderByMajorityRule(String name);
}
