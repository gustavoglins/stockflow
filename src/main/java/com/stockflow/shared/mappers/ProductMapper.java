package com.stockflow.shared.mappers;

import com.stockflow.api.requests.product.ProductRequestDTO;
import com.stockflow.api.responses.product.ProductResponseDTO;
import com.stockflow.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product toEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toResponse(Product product);

    List<ProductResponseDTO> toResponseList(List<Product> productList);
}
