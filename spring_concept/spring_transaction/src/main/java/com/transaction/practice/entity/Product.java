package com.transaction.practice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NonNull private String productName;
}
