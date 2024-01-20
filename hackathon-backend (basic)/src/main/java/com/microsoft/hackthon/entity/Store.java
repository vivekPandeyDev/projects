package com.microsoft.hackthon.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID storeId;

    private String storeName;

    @OneToMany(mappedBy = "store")
    private List<Product> productList;

    @Embedded
    private Address address;
}
