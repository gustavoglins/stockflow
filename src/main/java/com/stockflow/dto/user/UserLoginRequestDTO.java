package com.stockflow.dto.user;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

public record UserLoginRequestDTO(

        @NotBlank(message = "Login is required")
        String login,

        @NotBlank(message = "Password is required")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserLoginRequestDTO() {
        this("", "");
    }
}
