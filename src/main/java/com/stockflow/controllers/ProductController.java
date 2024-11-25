package com.stockflow.controllers;

import com.stockflow.dto.productDtos.ProductRequestDTO;
import com.stockflow.dto.productDtos.ProductResponseDTO;
import com.stockflow.services.ProductService;
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
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(service.create(productRequestDTO));
    }

    @PutMapping
    public ResponseEntity<ProductResponseDTO> update(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(service.update(productRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
