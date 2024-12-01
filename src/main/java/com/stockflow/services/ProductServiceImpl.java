package com.stockflow.services;

import com.stockflow.dto.product.ProductRequestDTO;
import com.stockflow.dto.product.ProductResponseDTO;
import com.stockflow.model.product.Product;
import com.stockflow.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    private Boolean dataValidation(ProductRequestDTO productRequestDTO) {
        if (repository.existsByName(productRequestDTO.name())) {
            throw new RuntimeException("Name: " + productRequestDTO.name() + " already registered");
        }
        return true;
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        if (dataValidation(productRequestDTO)) {
            return new ProductResponseDTO(repository.save(new Product(productRequestDTO)));
        } else {
            throw new RuntimeException("Unexpected error. Product not created");
        }
    }

    @Override
    public ProductResponseDTO update(ProductRequestDTO productRequestDTO) {
        Product productToUpdate = repository.findById(productRequestDTO.id())
                .orElseThrow(() -> new RuntimeException("Product with ID: " + productRequestDTO.id() + " not found"));

        if (!productToUpdate.getName().equals(productRequestDTO.name())) {
            if (repository.existsByName(productRequestDTO.name())) {
                throw new RuntimeException("Name " + productRequestDTO.name() + " already registered");
            }
        }

        productToUpdate.setName(productRequestDTO.name());
        productToUpdate.setImage(productRequestDTO.image());
        productToUpdate.setDescription(productRequestDTO.description());
        productToUpdate.setBuyPrice(productRequestDTO.buyPrice());
        productToUpdate.setSellPrice(productRequestDTO.sellPrice());
        productToUpdate.setQuantity(productRequestDTO.quantity());

        return new ProductResponseDTO(repository.save(productToUpdate));
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID: " + id + " not found"));

        return new ProductResponseDTO(existingProduct);
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isPresent()) repository.deleteById(id);
        else throw new RuntimeException("Product with ID: " + id + " not found");
    }
}
