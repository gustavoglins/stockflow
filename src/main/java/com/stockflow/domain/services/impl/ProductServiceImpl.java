package com.stockflow.domain.services.impl;

import com.stockflow.api.requests.product.ProductRequestDTO;
import com.stockflow.api.responses.product.ProductResponseDTO;
import com.stockflow.domain.entities.Product;
import com.stockflow.domain.entities.User;
import com.stockflow.domain.repositories.ProductRepository;
import com.stockflow.domain.repositories.UserRepository;
import com.stockflow.domain.services.interfaces.ProductService;
import com.stockflow.exceptions.DataAlreadyInUseException;
import com.stockflow.exceptions.DataNotFoundException;
import com.stockflow.shared.mappers.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository repository, UserRepository userRepository, ProductMapper productMapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        logger.info("Attempting to create a product with name: {}", productRequestDTO.name());

        if (repository.existsByName(productRequestDTO.name())) { // Checks if this product is already registered
            logger.warn("Product creation failed: product with name {} already exists.", productRequestDTO.name());
            throw new DataAlreadyInUseException("Product already registered");
        }

        User user = userRepository.findById(productRequestDTO.userId())
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", productRequestDTO.userId());
                    return new DataNotFoundException("User not found with ID: " + productRequestDTO.userId());
                });

        Product newProduct = productMapper.toEntity(productRequestDTO);
        newProduct.setUser(user);
        Product savedProduct = repository.save(newProduct);

        logger.info("Product created successfully with ID: {}", savedProduct.getId());

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponseDTO update(ProductRequestDTO productRequestDTO) {
        logger.info("Attempting to update product with ID: {}", productRequestDTO.id());

        Optional<Product> optionalProduct = repository.findById(productRequestDTO.id());
        if (optionalProduct.isPresent()) {
            Product retrievedProduct = optionalProduct.get();

            retrievedProduct.setName(productRequestDTO.name());
            retrievedProduct.setDescription(productRequestDTO.description());
            retrievedProduct.setPrice(productRequestDTO.price());
            retrievedProduct.setQuantity(productRequestDTO.quantity());

            Product updatedProduct = repository.save(retrievedProduct);
            logger.info("Product with ID: {} updated successfully.", updatedProduct.getId());

            return productMapper.toResponse(updatedProduct);
        } else {
            logger.error("Product with ID: {} not found for update.", productRequestDTO.id());
            throw new DataNotFoundException("Product not found");
        }
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        logger.info("Searching for product with ID: {}", id);

        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            logger.info("Product found with ID: {}", id);
            return productMapper.toResponse(optionalProduct.get());
        } else {
            logger.error("Product with ID: {} not found.", id);
            throw new DataNotFoundException("Product not found");
        }
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        logger.info("Retrieving all products.");
        List<Product> productList = repository.findAll();
        logger.info("Total products retrieved: {}", productList.size());
        return productMapper.toResponseList(productList);
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete product with ID: {}", id);

        if (!repository.existsById(id)) {
            logger.error("Product with ID: {} not found for deletion.", id);
            throw new DataNotFoundException("Product not found");
        }

        repository.deleteById(id);
        logger.info("Product with ID: {} deleted successfully.", id);
    }
}
