package com.stockflow.dto.team;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.stockflow.model.team.Team}
 */
public record TeamRequestDTO(

        UUID id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email is required")
        String login,

        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeamRequestDTO() {
        this(null, "", "", "");
    }
}
