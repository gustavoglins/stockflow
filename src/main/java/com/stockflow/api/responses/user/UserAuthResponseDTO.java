package com.stockflow.api.responses.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"login", "token"})
public record UserAuthResponseDTO(

        String login,

        String token
) {
}
