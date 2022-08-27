package com.favtuts.swagger.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public-api")
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any())
                //.paths( regex("/employee/*"))
                .paths(postPaths())
                .build().apiInfo(apiInfo());
    }

    private Predicate<String> postPaths() {
        return or(regex("/employee/*"), regex("/test/*"),regex("/api/posts.*"), regex("/api/favtuts.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Java Favtuts API")
                .description("Java Hungry API's")
                .version("1.0.0")
                .termsOfServiceUrl("https://www.favtuts.com")
                .license("Favtuts License")
                .licenseUrl("https://www.favtuts.com")
                .build();
    }
}
