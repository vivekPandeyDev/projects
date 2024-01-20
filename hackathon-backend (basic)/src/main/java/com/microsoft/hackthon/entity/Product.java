package com.microsoft.hackthon.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    private String productName;

    private Double productQuantity;

    private Double productPrice;

    @ManyToOne
    private Store store;

    private String description;

    private String category;

    private Double rating;
}
