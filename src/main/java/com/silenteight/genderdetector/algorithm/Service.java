package com.silenteight.genderdetector.algorithm;

public interface Service {
    String detectGenderByFirstTokenOfName(String name);
    String detectGenderByMajorityRule(String name);
}
