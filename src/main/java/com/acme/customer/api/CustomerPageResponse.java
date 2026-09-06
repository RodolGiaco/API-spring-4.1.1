package com.acme.customer.api;

import java.util.List;

public record CustomerPageResponse(
        List<CustomerResponse> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}