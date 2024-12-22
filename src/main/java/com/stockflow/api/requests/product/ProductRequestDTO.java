package com.stockflow.api.requests.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record ProductRequestDTO(

        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @PositiveOrZero(message = "Price is required")
        Double price,

        @PositiveOrZero(message = "Quantity is required")
        Integer quantity,

        @NotNull(message = "User ID is required")
        UUID userId
) {
}
