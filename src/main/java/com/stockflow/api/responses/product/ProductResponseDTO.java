package com.stockflow.api.responses.product;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"id", "name", "description", "price", "quantity", "userId", "userName"})
public record ProductResponseDTO(

        Long id,

        String name,

        String description,

        Double price,

        Integer quantity,

        UUID userId,

        String userName
) {
}
