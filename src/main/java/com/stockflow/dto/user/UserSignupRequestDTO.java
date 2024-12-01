package com.stockflow.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public record UserSignupRequestDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Login is required")
        String login,

        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserSignupRequestDTO() {
        this("", "", "");
    }
}
