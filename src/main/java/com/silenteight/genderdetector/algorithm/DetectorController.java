package com.silenteight.genderdetector.algorithm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gender")
public class DetectorController {

    private final DetectorService_v1 detector;

    public DetectorController(DetectorService_v1 detector) {
        this.detector = detector;
    }

    @GetMapping("")
    public Object getGender(@RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "1") int algoVariant) throws JsonProcessingException {
        String gender;
        if (algoVariant == 1) {
            gender = detector.detectGenderByFirstTokenOfName(name);
        } else {
            gender = detector.detectGenderByMajorityRule(name);
        }
        String json = "{\"gender\" : \"" + gender + "\"}";
        return new ObjectMapper().readValue(json, Object.class);
    }

}
