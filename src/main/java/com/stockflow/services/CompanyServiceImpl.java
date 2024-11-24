package com.stockflow.services;

import com.stockflow.dto.companyDtos.CompanyRequestDTO;
import com.stockflow.dto.companyDtos.CompanyResponseDTO;
import com.stockflow.model.company.Company;
import com.stockflow.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompanyResponseDTO create(CompanyRequestDTO data) {
        return new CompanyResponseDTO(repository.save(new Company(data)));
    }

    @Override
    public CompanyResponseDTO update(CompanyRequestDTO companyRequestDTO) {
        Optional<Company> optionalCompany = repository.findById(companyRequestDTO.id());
        if (optionalCompany.isPresent()) {
            Company selectedCompany = optionalCompany.get();

            selectedCompany.setName(companyRequestDTO.name());
            selectedCompany.setLegalName(companyRequestDTO.legalName());
            selectedCompany.setTaxId(companyRequestDTO.taxId());

            return new CompanyResponseDTO(repository.save(selectedCompany));
        } else {
            throw new RuntimeException("Company not found");
        }
    }

    @Override
    public CompanyResponseDTO findById(UUID id) {
        Optional<Company> optionalCompany = repository.findById(id);
        if (optionalCompany.isPresent()) return new CompanyResponseDTO(optionalCompany.get());
        else throw new RuntimeException("Company not found");
    }

    @Override
    public List<CompanyResponseDTO> findAll() {
        List<Company> companyList = repository.findAll();

        return companyList.stream()
                .map(CompanyResponseDTO::new)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        Optional<Company> optionalCompany = repository.findById(id);
        if (optionalCompany.isPresent()) repository.deleteById(id);
        else throw new RuntimeException("Company not found");
    }
}
