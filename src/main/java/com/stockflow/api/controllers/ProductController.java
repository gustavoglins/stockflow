package com.stockflow.api.controllers;

import com.stockflow.api.requests.product.ProductRequestDTO;
import com.stockflow.api.responses.product.ProductDetailsResponseDTO;
import com.stockflow.domain.services.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Endpoints related to product management")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create a new product",
            description = "Creates a new product with the provided information",
            tags = {"Product"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Product created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDetailsResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request body, missing fields, or invalid data format"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity, validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @PostMapping
    public ResponseEntity<ProductDetailsResponseDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        logger.info("Received request to create a new product");
        return ResponseEntity.ok(service.create(productRequestDTO));
    }

    @Operation(
            summary = "Update an existing product",
            description = "Updates the details of an existing product with the provided information",
            tags = {"Product"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Product updated successfully",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = ProductDetailsResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request body, missing fields, or invalid data format"),
            @ApiResponse(responseCode = "404", description = "Product not found with the provided ID"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity, validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @PutMapping
    public ResponseEntity<ProductDetailsResponseDTO> update(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        logger.info("Received request to update an existing product");
        return ResponseEntity.ok(service.update(productRequestDTO));
    }

    @Operation(
            summary = "Get product by ID",
            description = "Retrieve the product details for the given product ID",
            tags = {"Product"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Product found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDetailsResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid product ID format"),
            @ApiResponse(responseCode = "404", description = "Product not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsResponseDTO> findById(@PathVariable Long id) {
        logger.info("Received request to find product by ID");
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Get all products",
            description = "Retrieve a list of all products available",
            tags = {"Product"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Products found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDetailsResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @GetMapping
    public ResponseEntity<List<ProductDetailsResponseDTO>> findAll() {
        logger.info("Received request to find all products");
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Delete product by ID",
            description = "Delete the product with the specified ID",
            tags = {"Product"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID format"),
            @ApiResponse(responseCode = "404", description = "Product not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Received request to delete product by ID");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
