package com.stockflow.api.responses.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.domain.entities.User;
import com.stockflow.shared.enums.AuthRoles;

import java.util.UUID;

/**
 * DTO for {@link User}
 */
@JsonPropertyOrder({"id", "name", "login", "role"})
public record UserResponseDTO(

        UUID id,

        String name,

        String login,

        AuthRoles role
) {
}
