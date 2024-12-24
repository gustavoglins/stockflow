package com.stockflow.api.responses.product;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.domain.entities.Product;

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

    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getUser().getId(),
                product.getUser().getName());
    }
}
