package com.stockflow.services;

import com.stockflow.dto.company.CompanyRequestDTO;
import com.stockflow.dto.company.CompanyResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    CompanyResponseDTO create(CompanyRequestDTO companyRequestDTO);

    CompanyResponseDTO update(CompanyRequestDTO companyRequestDTO);

    CompanyResponseDTO findById(UUID id);

    List<CompanyResponseDTO> findAll();

    void delete(UUID id);
}
