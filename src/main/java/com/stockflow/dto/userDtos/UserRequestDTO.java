package com.stockflow.dto.userDtos;

import com.stockflow.model.company.Company;
import com.stockflow.model.team.Team;
import com.stockflow.model.user.User;
import com.stockflow.model.user.UserRole;
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

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must have at least 8 characters")
        String password,

        Company company,

        Team team,

        UserRole role) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}