package com.stockflow.domain.services.interfaces;

import com.stockflow.api.requests.product.ProductRequestDTO;
import com.stockflow.api.responses.product.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO create(ProductRequestDTO productRequestDTO);

    ProductResponseDTO update(ProductRequestDTO productRequestDTO);

    ProductResponseDTO findById(Long id);

    List<ProductResponseDTO> findAll();

    void delete(Long id);
}
