package com.stockflow.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.stockflow.model.company.Company}
 */
public record CompanyRequestDTO(

        UUID id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Legal name is required")
        String legalName,

        @NotBlank(message = "Tax ID is required")
        String taxId,

        @NotBlank(message = "Contact phone is required")
        String contactPhone,

        @NotBlank(message = "Company email is required")
        String login,

        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public CompanyRequestDTO() {
        this(null, "", "", "", "", "", "");
    }
}
