package com.microsoft.hackthon.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsoft.hackthon.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
