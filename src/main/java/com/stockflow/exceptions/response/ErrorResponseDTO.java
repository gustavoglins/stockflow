package com.stockflow.exceptions.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message, details, statusCode"})
public record ErrorResponseDTO(

        String message,

        String details,

        int statusCode
) {
}
