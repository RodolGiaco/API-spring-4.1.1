package com.acme.customer.domain;

public record CustomerProfile(
        Long customerId,
        String status
) {
}