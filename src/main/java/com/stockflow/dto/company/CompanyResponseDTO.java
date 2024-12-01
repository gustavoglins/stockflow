package com.stockflow.dto.company;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockflow.model.company.Company;
import com.stockflow.model.employee.Employee;
import com.stockflow.model.product.Product;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.stockflow.model.company.Company}
 */
@JsonPropertyOrder({"id", "name", "legalName", "taxId", "contactPhone", "login", "password", "employees", "products"})
public record CompanyResponseDTO(

        @JsonPropertyOrder("id")
        UUID id,

        @JsonPropertyOrder("name")
        String name,

        @JsonPropertyOrder("legalName")
        String legalName,

        @JsonPropertyOrder("taxId")
        String taxId,

        @JsonPropertyOrder("contactPhone")
        String contactPhone,

        @JsonPropertyOrder("login")
        String login,

        @JsonPropertyOrder("password")
        String password,

        @JsonPropertyOrder("users")
        List<Employee> employees,

        @JsonPropertyOrder("products")
        List<Product> products) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public CompanyResponseDTO(Company company) {
        this(
                company.getId(),
                company.getName(),
                company.getLegalName(),
                company.getTaxId(),
                company.getContactPhone(),
                company.getLogin(),
                company.getPassword(),
                company.getEmployees(),
                company.getProducts());
    }
}
