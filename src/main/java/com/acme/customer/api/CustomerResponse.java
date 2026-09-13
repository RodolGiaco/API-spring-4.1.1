package com.acme.customer.api;

import io.swagger.v3.oas.annotations.media.Schema;

public record CustomerResponse(

        @Schema(
                description = "Customer identifier",
                example = "42"
        )
        Long id,

        @Schema(
                description = "Customer full name",
                example = "John Doe"
        )
        String name,

        @Schema(
                description = "Customer email address",
                example = "john@example.com"
        )
        String email

) {
}