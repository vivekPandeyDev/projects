package com.microsoft.hackthon.service.impl;

import com.microsoft.hackthon.dao.ProductRepository;
import com.microsoft.hackthon.dto.ProductDto;
import com.microsoft.hackthon.entity.Product;
import com.microsoft.hackthon.exception.ProductNotFound;
import com.microsoft.hackthon.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Override
    public List<ProductDto> getProductsDto() {
        final var products = productRepository.findAll();
        return products.stream().map(product -> entityToDto(product)).toList();
    
    }

    @Override
    public Product getProduct(UUID id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("Product not found with id: " + id));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        // create a method to update product to database with product repository
        Product existingProduct = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new ProductNotFound("Product not found with id: " + product.getProductId()));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setProductQuantity(product.getProductQuantity());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setRating(product.getRating());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFound("Product not found with id: " + id));
        productRepository.deleteById(id);
    }

    @Override
    public Product dtoToEntity(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto entityToDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

}
