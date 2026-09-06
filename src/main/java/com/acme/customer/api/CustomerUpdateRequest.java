package com.acme.customer.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerUpdateRequest(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email

) {
}