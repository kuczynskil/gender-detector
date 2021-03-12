package com.silenteight.genderdetector.controller;

import com.silenteight.genderdetector.GenderDetectorApplication;
import com.silenteight.genderdetector.controller.DetectorController;
import com.silenteight.genderdetector.service.DetectorService_v1;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DetectorController.class)
@ContextConfiguration(classes = GenderDetectorApplication.class)
class DetectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetectorService_v1 detector;

    @Test
    void getGender() throws Exception {
        String MALE = "MALE";
        String INCONCLUSIVE = "INCONCLUSIVE";
        String FEMALE = "MALE";

        test_gender_by_first_given_name("marek", MALE);
        test_gender_by_first_given_name("", INCONCLUSIVE);
        test_gender_by_first_given_name("monika", FEMALE);
        test_gender_by_first_given_name("adam marzena 458", MALE);
        test_gender_by_first_given_name("M0nik4", INCONCLUSIVE);

        test_gender_by_majority_rule("marek monika", INCONCLUSIVE);
        test_gender_by_majority_rule("marek monika Agnieszka", FEMALE);
        test_gender_by_majority_rule(" ", INCONCLUSIVE);
        test_gender_by_majority_rule("bogdan", MALE);
        test_gender_by_majority_rule("sławomir abc123", MALE);
    }

    @Test
    void returns_available_female_and_male_tokens() throws Exception {
        mockMvc.perform(get("/gender/tokens").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.female_tokens", Matchers.hasSize(Matchers.greaterThan(10000))))
                .andExpect(jsonPath("$.male_tokens", Matchers.hasSize(Matchers.greaterThan(10000))));
    }

    private void test_gender_by_first_given_name(String name, String expectedResult) throws Exception {
        Mockito.when(detector.detectGenderByFirstTokenOfName(name)).thenReturn(expectedResult);
        mockMvc_test(name, expectedResult, 1);
    }

    private void test_gender_by_majority_rule(String names, String expectedResult) throws Exception {
        Mockito.when(detector.detectGenderByMajorityRule(names)).thenReturn(expectedResult);
        mockMvc_test(names, expectedResult, 2);
    }

    private void mockMvc_test(String name, String expectedResult, int algorithmVariant) throws Exception {
        mockMvc.perform(get("/gender?name=" + name + "&algoVariant=" + algorithmVariant)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gender", Matchers.is(expectedResult)));
    }
}