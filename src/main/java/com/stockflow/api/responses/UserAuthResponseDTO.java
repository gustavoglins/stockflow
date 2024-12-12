package com.stockflow.api.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"login", "token"})
public record UserAuthResponseDTO(

        String login,

        String token
) {
}
