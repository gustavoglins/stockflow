package com.stockflow.dto.companyDtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.company.Company;
import com.stockflow.model.product.Product;
import com.stockflow.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.stockflow.model.company.Company}
 */
@JsonPropertyOrder({"id", "name", "legalName", "taxId", "users", "products"})
public record CompanyResponseDTO(

        @JsonPropertyOrder("id")
        UUID id,

        @JsonPropertyOrder("name")
        String name,

        @JsonPropertyOrder("legalName")
        String legalName,

        @JsonPropertyOrder("taxId")
        String taxId,

        @JsonPropertyOrder("users")
        List<User> users,

        @JsonPropertyOrder("products")
        List<Product> products) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public CompanyResponseDTO(Company company) {
        this(company.getId(), company.getName(), company.getLegalName(), company.getTaxId(), company.getUsers(), company.getProducts());
    }
}
