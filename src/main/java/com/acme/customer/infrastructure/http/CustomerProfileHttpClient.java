package com.acme.customer.infrastructure.http;

import com.acme.customer.application.port.CustomerProfileClient;
import com.acme.customer.domain.CustomerProfile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CustomerProfileHttpClient
        implements CustomerProfileClient {

    private final RestClient restClient;

    public CustomerProfileHttpClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public CustomerProfile findByCustomerId(Long customerId) {

        CustomerProfileResponse response = restClient
                .get()
                .uri("/api/v1/profiles/{customerId}", customerId)
                .retrieve()
                .body(CustomerProfileResponse.class);

        if (response == null) {
            throw new IllegalStateException(
                    "External customer profile response is empty"
            );
        }

        return new CustomerProfile(
                response.customerId(),
                response.status()
        );
    }
}