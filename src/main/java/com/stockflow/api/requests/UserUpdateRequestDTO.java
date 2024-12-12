package com.stockflow.api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserUpdateRequestDTO(

        @NotBlank(message = "ID is required")
        UUID id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Login is required")
        String login,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {
}
