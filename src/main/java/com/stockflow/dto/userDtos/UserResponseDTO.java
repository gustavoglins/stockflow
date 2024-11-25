package com.stockflow.dto.userDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.company.Company;
import com.stockflow.model.user.User;
import com.stockflow.model.user.UserRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
@JsonPropertyOrder({"id", "name", "login", "password", "role", "company"})
public record UserResponseDTO(

        @JsonProperty("id")
        UUID id,

        @JsonProperty("name")
        String name,

        @JsonProperty("login")
        String login,

        @JsonProperty("password")
        String password,

        @JsonProperty("company")
        Company company,

        @JsonProperty("role")
        UserRole role) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserResponseDTO(User user){
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getCompany(), user.getRole());
    }
}