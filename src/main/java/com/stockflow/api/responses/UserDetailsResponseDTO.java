package com.stockflow.api.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.domain.entities.Product;
import com.stockflow.domain.entities.User;
import com.stockflow.shared.enums.AuthRoles;

import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
@JsonPropertyOrder({"id", "name", "login", "password", "role", "productList"})
public record UserDetailsResponseDTO(

        UUID id,

        String name,

        String login,

        String password,

        AuthRoles role,

        List<Product> productList
) {

    public UserDetailsResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                user.getProductList()
        );
    }
}
