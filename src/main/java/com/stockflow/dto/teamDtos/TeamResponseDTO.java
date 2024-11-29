package com.stockflow.dto.teamDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.collaborator.Collaborator;
import com.stockflow.model.product.Product;
import com.stockflow.model.team.Team;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.stockflow.model.team.Team}
 */
@JsonPropertyOrder({"id", "name", "login", "password", "users", "products"})
public record TeamResponseDTO(

        @JsonProperty("id")
        UUID id,

        @JsonProperty("name")
        String name,

        @JsonProperty("login")
        String login,

        @JsonProperty("password")
        String password,

        @JsonProperty("users")
        List<Collaborator> collaborators,

        @JsonProperty("products")
        List<Product> products) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeamResponseDTO(Team team) {
        this(team.getId(), team.getName(), team.getLogin(), team.getPassword(), team.getCollaborators(), team.getProducts());
    }
}
