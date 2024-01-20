package com.vivek.pandey.productservice.repository;

import com.vivek.pandey.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
