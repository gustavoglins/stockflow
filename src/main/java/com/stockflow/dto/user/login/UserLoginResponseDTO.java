package com.stockflow.dto.user.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder("token")
public record UserLoginResponseDTO(

        @JsonProperty("token")
        String token) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
