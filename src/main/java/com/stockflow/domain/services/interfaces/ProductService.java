package com.stockflow.domain.services.interfaces;

import com.stockflow.api.requests.product.ProductRequestDTO;
import com.stockflow.api.responses.product.ProductDetailsResponseDTO;

import java.util.List;

public interface ProductService {

    ProductDetailsResponseDTO create(ProductRequestDTO productRequestDTO);

    ProductDetailsResponseDTO update(ProductRequestDTO productRequestDTO);

    ProductDetailsResponseDTO findById(Long id);

    List<ProductDetailsResponseDTO> findAll();

    void delete(Long id);
}
