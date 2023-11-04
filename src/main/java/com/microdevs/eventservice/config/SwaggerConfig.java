package com.microdevs.eventservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("MicroDevs")
                .displayName("Micro Devs")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Service Application API")
                        .contact(new Contact()
                                .email("muhammetemrevatan@gmail.com")
                                .url("https://github.com/Micro-Devs/event-service")
                                .name("Event Service Developer"))
                        .description("Event Service Application Open Api Documentation")
                        .license(new License()
                                .name("Apache License Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .version("0.0.1-SNAPSHOT"));

    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
            operation.addParametersItem(new HeaderParameter()
                    .name("X-Custom-Event-Header")
                    .required(false)
                    .description("A custom header to provide additional information."));
        }));
    }
}
