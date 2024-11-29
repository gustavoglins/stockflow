package com.stockflow.dto.userDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.company.Company;
import com.stockflow.model.team.Team;
import com.stockflow.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
@JsonPropertyOrder({"id", "name", "login", "password", "company", "team", "role"})
public record UserResponseDTO(

        @JsonProperty("id")
        UUID id,

        @JsonProperty("name")
        String name,

        @JsonProperty("login")
        String login,

        @JsonProperty("password")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword());
    }
}