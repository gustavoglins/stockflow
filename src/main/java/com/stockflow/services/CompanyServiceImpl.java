package com.stockflow.services;

import com.stockflow.dto.companyDtos.CompanyRequestDTO;
import com.stockflow.dto.companyDtos.CompanyResponseDTO;
import com.stockflow.model.company.Company;
import com.stockflow.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    private Boolean dataValidation(CompanyRequestDTO companyRequestDTO) {
        if (repository.existsByName(companyRequestDTO.name())) {
            throw new RuntimeException("Name " + companyRequestDTO.name() + " already registered");
        }
        if (repository.existsByLegalName(companyRequestDTO.legalName())) {
            throw new RuntimeException("Legal name " + companyRequestDTO.legalName() + " already registered");
        }
        if (repository.existsByTaxId(companyRequestDTO.taxId())) {
            throw new RuntimeException("Tax ID " + companyRequestDTO.taxId() + " already registered");
        }
        return true;
    }

    @Override
    public CompanyResponseDTO create(CompanyRequestDTO companyRequestDTO) {
        if (dataValidation(companyRequestDTO)) {
            return new CompanyResponseDTO(repository.save(new Company(companyRequestDTO)));
        } else throw new RuntimeException("Unexpected error. Company not created");
    }

    @Override
    public CompanyResponseDTO update(CompanyRequestDTO companyRequestDTO) {
        Company companyToUpdate = repository.findById(companyRequestDTO.id())
                .orElseThrow(() -> new RuntimeException("Company with ID: " + companyRequestDTO.id() + " not found"));

        if (!companyToUpdate.getName().equals(companyRequestDTO.name())) {
            if (repository.existsByName(companyRequestDTO.name())) {
                throw new RuntimeException("Name " + companyRequestDTO.name() + " already registered");
            }
        }
        if (!companyToUpdate.getLegalName().equals(companyRequestDTO.name())) {
            if (repository.existsByLegalName(companyRequestDTO.legalName())) {
                throw new RuntimeException("Legal name " + companyRequestDTO.legalName() + " already registered");
            }
        }
        if (!companyToUpdate.getTaxId().equals(companyRequestDTO.taxId())) {
            if (repository.existsByTaxId(companyRequestDTO.taxId())) {
                throw new RuntimeException("Tax ID " + companyRequestDTO.taxId() + " already registered");
            }
        }

        companyToUpdate.setName(companyRequestDTO.name());
        companyToUpdate.setLegalName(companyRequestDTO.legalName());
        companyToUpdate.setTaxId(companyRequestDTO.taxId());

        return new CompanyResponseDTO(repository.save(companyToUpdate));
    }

    @Override
    public CompanyResponseDTO findById(UUID id) {
        Company existingCompany = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company with ID: " + id + " not found"));

        return new CompanyResponseDTO(existingCompany);
    }

    @Override
    public List<CompanyResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(CompanyResponseDTO::new)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (repository.findById(id).isPresent()) repository.deleteById(id);
        else throw new RuntimeException("Company with ID: " + id + " not found");
    }
}
