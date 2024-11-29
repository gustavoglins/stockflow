package com.stockflow.dto.userDtos;

import com.stockflow.model.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
public record UserRequestDTO(

        UUID id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Login is required")
        String login,

        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserRequestDTO() {
        this(null, "", "", "");
    }
}