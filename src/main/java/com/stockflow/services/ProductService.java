package com.stockflow.services;

import com.stockflow.dto.product.ProductRequestDTO;
import com.stockflow.dto.product.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO create(ProductRequestDTO productRequestDTO);

    ProductResponseDTO update(ProductRequestDTO productRequestDTO);

    ProductResponseDTO findById(Long id);

    List<ProductResponseDTO> findAll();

    void delete(Long id);
}
