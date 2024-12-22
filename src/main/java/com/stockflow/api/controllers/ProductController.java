package com.stockflow.api.controllers;

import com.stockflow.api.requests.product.ProductRequestDTO;
import com.stockflow.api.responses.product.ProductDetailsResponseDTO;
import com.stockflow.domain.services.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDetailsResponseDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(service.create(productRequestDTO));
    }

    @PutMapping
    public ResponseEntity<ProductDetailsResponseDTO> update(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(service.update(productRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailsResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
