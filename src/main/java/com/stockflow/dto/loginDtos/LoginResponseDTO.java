package com.stockflow.dto.loginDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({"accessToken", "expiresIn"})
public record LoginResponseDTO(

        @JsonProperty("accessToken")
        String accessToken,

        @JsonProperty("expiresIn")
        Long expiresIn) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
