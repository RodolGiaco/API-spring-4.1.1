package com.acme.customer.domain;

public record Customer(
        Long id,
        String name,
        String email
) {
}