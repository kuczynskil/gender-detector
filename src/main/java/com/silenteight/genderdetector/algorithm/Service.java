package com.silenteight.genderdetector.algorithm;

public interface Service {
    boolean detectGenderByFirstTokenOfName();
    boolean detectGenderByMajorityRule();
}
