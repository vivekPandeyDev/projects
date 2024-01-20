package com.microsoft.hackthon.service;

import com.microsoft.hackthon.dto.ProductDto;
import com.microsoft.hackthon.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    public List<ProductDto> getProductsDto();

    public ProductDto getProductDto(UUID id);

    public ProductDto addProduct(ProductDto product);

    public ProductDto updateProduct(ProductDto product);

    public void deleteProduct(UUID id);

    public static Product dtoToEntity(ProductDto productDto);

    public static ProductDto entityToDto(Product product);

}
