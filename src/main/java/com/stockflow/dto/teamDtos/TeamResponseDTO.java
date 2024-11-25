package com.stockflow.dto.teamDtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.product.Product;
import com.stockflow.model.team.Team;
import com.stockflow.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.stockflow.model.team.Team}
 */
@JsonPropertyOrder({"id", "name", "users", "products"})
public record TeamResponseDTO(

        UUID id,

        String name,

        @JsonPropertyOrder("users")
        List<User> users,

        @JsonPropertyOrder("products")
        List<Product> products) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeamResponseDTO(Team team){
        this(team.getId(), team.getName(), team.getUsers(), team.getProducts());
    }
}
