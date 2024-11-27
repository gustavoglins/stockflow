package com.stockflow.controllers;

import com.stockflow.dto.companyDtos.CompanyRequestDTO;
import com.stockflow.dto.companyDtos.CompanyResponseDTO;
import com.stockflow.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> crete(@ModelAttribute @Valid CompanyRequestDTO companyRequestDTO) {
        return ResponseEntity.ok(service.create(companyRequestDTO));
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
