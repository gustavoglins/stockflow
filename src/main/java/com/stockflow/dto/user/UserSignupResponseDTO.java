package com.stockflow.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.role.Role;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@JsonPropertyOrder({"id", "name", "login", "password", "role"})
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
        Role role) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
