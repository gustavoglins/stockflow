package com.stockflow.api.responses.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.domain.entities.User;
import com.stockflow.shared.enums.AuthRoles;

import java.util.UUID;

/**
 * DTO for {@link User}
 */
@JsonPropertyOrder({"id", "name", "login", "role"})
public record UserDetailsResponseDTO(

        UUID id,

        String name,

        String login,

        AuthRoles role
) {

    public UserDetailsResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getRole()
        );
    }
}
