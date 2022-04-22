package com.java.coach.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.java.coach"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }


    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("API JAVA COACH")
                .version("1.0")
                .description("Api para aprender el lenguaje Java.")
                .contact(new Contact("Gabriela Soruco", "https://github.com/GabrielaSoruco", "soruco.gabi@gmail.com"))
                .license("license")
                .build();
    }
}