package com.stockflow.controllers;

import com.stockflow.dto.companyDtos.CompanyRequestDTO;
import com.stockflow.dto.companyDtos.CompanyResponseDTO;
import com.stockflow.exceptions.EntityValidationException;
import com.stockflow.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@ModelAttribute @Valid CompanyRequestDTO companyRequestDTO, BindingResult result) {
        try {
            service.create(companyRequestDTO);

        } catch (EntityValidationException exception) {
            if (exception.getMessage().contains("Name")) {
                result.rejectValue("name", null, exception.getMessage());
            } else if (exception.getMessage().contains("Legal name")) {
                result.rejectValue("legalName", null, exception.getMessage());
            } else if (exception.getMessage().contains("Tax ID")) {
                result.rejectValue("taxId", null, exception.getMessage());
            } else if (exception.getMessage().contains("Contact phone")) {
                result.rejectValue("contactPhone", null, exception.getMessage());
            } else if (exception.getMessage().contains("Email")) {
                result.rejectValue("login", null, exception.getMessage());
            } else if (exception.getMessage().contains("Password")) {
                result.rejectValue("password", null, exception.getMessage());
            }
        }

        if (result.hasErrors()) {
            return "signup-company";
        }

        return "redirect:/dashboard";
    }

    @PutMapping
    public ResponseEntity<CompanyResponseDTO> update(@ModelAttribute @Valid CompanyRequestDTO companyRequestDTO) {
        return ResponseEntity.ok(service.update(companyRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}