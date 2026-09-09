package com.acme.customer.configuration;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "customer.external-api")
@Validated
public record CustomerApiProperties(

        @NotBlank
        String baseUrl

) {
}