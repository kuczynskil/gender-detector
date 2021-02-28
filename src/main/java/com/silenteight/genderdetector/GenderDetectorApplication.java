package com.silenteight.genderdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
public class GenderDetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenderDetectorApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.silenteight"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Gender-detector API",
                "API of an application, which detects a gender by given name/s",
                "1.0",
                "Free to use",
                new Contact("Łukasz Kuczyński", "https://github.com/kuczynskil",
                        "l.kuczynski95@gmail.com"),
                "API license",
                "https://github.com/kuczynskil",
                Collections.emptyList()
        );
    }
}
