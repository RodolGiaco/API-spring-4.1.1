package com.acme.customer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration(proxyBeanMethods = false)
public class RestClientConfiguration {

    @Bean
    RestClient customerProfileRestClient(
            RestClient.Builder builder,
            CustomerApiProperties properties) {

        return builder
                .baseUrl(properties.baseUrl())
                .build();
    }
}