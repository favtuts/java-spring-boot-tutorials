/*
package com.favtuts.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.function.Predicate;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

//Ref: https://stackoverflow.com/questions/53446447/support-multiple-pathmapping-in-swagger-ui-spring-boot
@Configuration
@EnableSwagger2
public class SwaggerSampleConfig {

    @Bean
    public Docket swaggerSettingsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Settings")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xyz"))
                .paths(regex("/secure/api/v1/settings/.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Settings API").build())
                .globalOperationParameters(operationParameters());
    }

    private List<Parameter> operationParameters() {
    }

    @Bean
    public Docket swaggerProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Product")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xyz.modules.v1"))
                .paths(productPaths())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Product API").build())
                .globalOperationParameters(operationParameters());
    }

    @Bean
    public Docket swaggerModuleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Others")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xyz.modules.v1"))
                .paths(postPaths())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Other Modules API").build())
                .globalOperationParameters(operationParameters());
    }

    private Predicate<String> postPaths() {
        return or(regex("^(?=\\/secure\\/api\\/v1\\/)(?!.*(settings|products)).+\\/.*"));
    }
}
*/
