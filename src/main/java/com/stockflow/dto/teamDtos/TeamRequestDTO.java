package com.stockflow.dto.teamDtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.stockflow.model.team.Team}
 */
public record TeamRequestDTO(

        UUID id,

        @NotBlank(message = "Name is required")
        String name) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
