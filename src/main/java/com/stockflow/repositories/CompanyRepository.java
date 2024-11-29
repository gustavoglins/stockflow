package com.stockflow.repositories;

import com.stockflow.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Boolean existsByName(String name);
    Boolean existsByLegalName(String legalName);
    Boolean existsByTaxId(String taxId);
    Boolean existsByContactPhone(String contactPhone);
    Boolean existsByLogin(String login);
}
