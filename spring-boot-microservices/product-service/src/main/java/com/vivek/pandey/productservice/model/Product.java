package com.vivek.pandey.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


import java.math.BigDecimal;

@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}
