package com.java.coach;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API JAVA COACH", version = "1.0", description = "Microservicio java coach"))
public class ApiJavaCoachApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiJavaCoachApplication.class, args);
    }

}
