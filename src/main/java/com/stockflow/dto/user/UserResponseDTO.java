package com.stockflow.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.product.Product;
import com.stockflow.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
@JsonPropertyOrder({"id", "name", "login", "password", "productList"})
public record UserResponseDTO(

        @JsonProperty("id")
        UUID id,

        @JsonProperty("name")
        String name,

        @JsonProperty("login")
        String login,

        @JsonProperty("password")
        String password,

        @JsonProperty("productList")
        List<Product> productList) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getProductList());
    }
}