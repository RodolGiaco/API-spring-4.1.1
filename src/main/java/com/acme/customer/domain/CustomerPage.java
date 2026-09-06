package com.acme.customer.domain;

import java.util.List;

public record CustomerPage(
        List<Customer> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}