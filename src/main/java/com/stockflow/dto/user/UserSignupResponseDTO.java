package com.stockflow.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.product.Product;
import com.stockflow.model.role.Role;
import com.stockflow.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"id", "name", "login", "password", "role", "productList"})
public record UserSignupResponseDTO(

        @JsonProperty("id")
        UUID id,

        @JsonProperty("name")
        String name,

        @JsonProperty("login")
        String login,

        @JsonProperty("password")
        String password,

        @JsonProperty("role")
        Role role,

        @JsonProperty("productList")
        List<Product> productList) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserSignupResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getRole(), user.getProductList());
    }
}
