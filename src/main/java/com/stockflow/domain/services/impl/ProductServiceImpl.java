package com.stockflow.domain.services.impl;

import com.stockflow.api.requests.product.ProductRequestDTO;
import com.stockflow.api.responses.product.ProductDetailsResponseDTO;
import com.stockflow.domain.entities.Product;
import com.stockflow.domain.entities.User;
import com.stockflow.domain.repositories.ProductRepository;
import com.stockflow.domain.repositories.UserRepository;
import com.stockflow.domain.services.interfaces.ProductService;
import com.stockflow.exceptions.DataAlreadyInUseException;
import com.stockflow.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public ProductDetailsResponseDTO create(ProductRequestDTO productRequestDTO) {
        if (repository.existsByName(productRequestDTO.name())) {
            throw new DataAlreadyInUseException("Product already registered");
        }

        User user = userRepository.findById(productRequestDTO.userId())
                .orElseThrow(() -> new DataNotFoundException("User not found with ID: " + productRequestDTO.userId()));

        Product newProduct = new Product(productRequestDTO, user);

        return new ProductDetailsResponseDTO(repository.save(newProduct));
    }

    @Override
    public ProductDetailsResponseDTO update(ProductRequestDTO productRequestDTO) {
        Optional<Product> optionalProduct = repository.findById(productRequestDTO.id());
        if (optionalProduct.isPresent()) {
            Product retrivedProduct = optionalProduct.get();

            retrivedProduct.setName(productRequestDTO.name());
            retrivedProduct.setDescription(productRequestDTO.description());
            retrivedProduct.setPrice(productRequestDTO.price());
            retrivedProduct.setQuantity(productRequestDTO.quantity());

            return new ProductDetailsResponseDTO(repository.save(retrivedProduct));
        } else {
            throw new DataAlreadyInUseException("Product not found");
        }
    }

    @Override
    public ProductDetailsResponseDTO findById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            return new ProductDetailsResponseDTO(optionalProduct.get());
        } else {
            throw new DataNotFoundException("Product not found");
        }
    }

    @Override
    public List<ProductDetailsResponseDTO> findAll() {
        return repository.findAll().stream().map(ProductDetailsResponseDTO::new).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
