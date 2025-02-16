package com.microservice.apigateway.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway Documentation")
                        .version("v1")
                        .description("Aggregated API documentation for all services through the API Gateway"));
    }

    @Bean
    public GroupedOpenApi allServicesApi() {
        return GroupedOpenApi.builder()
                .group("all-services")
                .pathsToMatch("/**")
                .build();
    }
}