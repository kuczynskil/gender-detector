package com.silenteight.genderdetector.algorithm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/gender")
public class DetectorController {

    private final DetectorService_v1 detector;

    public DetectorController(DetectorService_v1 detector) {
        this.detector = detector;
    }

    @GetMapping("")
    public Object getGender(@ApiParam(value = "name or names separated by a whitespace")
                            @RequestParam(defaultValue = "") String name,
                            @ApiParam(value = "variant of gender detecting algorithm:" +
                                    "\n1 - detects gender by first word" +
                                    "\n2 - gender is determined by majority rule (more male than female names -> MALE)")
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

    @GetMapping("/tokens")
    public Object getAvailableTokens() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/com/silenteight/genderdetector/algorithm/tokens.json");
        return mapper.readTree(file);
    }

}
