package com.acme.customer.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OpenApiConfiguration {

    @Bean
    OpenAPI customerApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer API")
                        .description(
                                "REST API for customer management"
                        )
                        .version("v1"));
    }
}