package com.stockflow.dto.product;

import com.stockflow.model.company.Company;
import com.stockflow.model.team.Team;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link com.stockflow.model.product.Product}
 */
public record ProductRequestDTO(

        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String image,

        @NotBlank(message = "Description is required")
        String description,

        @NotBlank(message = "Buy price is required")
        @Min(0)
        Double buyPrice,

        @NotBlank(message = "Sell price is required")
        @Min(0)
        Double sellPrice,

        @NotBlank(message = "Quantity is required")
        @Min(0)
        Integer quantity,

        Company company,

        Team team) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}