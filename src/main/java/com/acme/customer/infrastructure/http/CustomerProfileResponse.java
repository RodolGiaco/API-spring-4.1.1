package com.acme.customer.infrastructure.http;

public record CustomerProfileResponse(
        Long customerId,
        String status
) {
}