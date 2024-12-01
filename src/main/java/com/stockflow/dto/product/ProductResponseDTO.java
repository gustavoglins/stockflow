package com.stockflow.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.company.Company;
import com.stockflow.model.product.Product;
import com.stockflow.model.team.Team;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link com.stockflow.model.product.Product}
 */
@JsonPropertyOrder({"id", "name", "image", "description", "buyPrice", "sellPrice", "quantity", "company", "team"})
public record ProductResponseDTO(

        @JsonProperty("id")
        Long id,

        @JsonProperty("name")
        String name,

        @JsonProperty("image")
        String image,

        @JsonProperty("description")
        String description,

        @JsonProperty("buyPrice")
        Double buyPrice,

        @JsonProperty("sellPrice")
        Double sellPrice,

        @JsonProperty("quantity")
        Integer quantity,

        @JsonProperty("team")
        Company company,

        Team team) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getImage(), product.getDescription(), product.getBuyPrice(), product.getSellPrice(), product.getQuantity(), product.getCompany(), product.getTeam());
    }
}
