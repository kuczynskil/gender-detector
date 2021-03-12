package com.silenteight.genderdetector.service;

public interface DetectorService {
    String detectGenderByFirstTokenOfName(String name);
    String detectGenderByMajorityRule(String name);
}
