package com.stockflow.dto.companyDtos;

import jakarta.validation.constraints.NotBlank;

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
        String taxId) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
