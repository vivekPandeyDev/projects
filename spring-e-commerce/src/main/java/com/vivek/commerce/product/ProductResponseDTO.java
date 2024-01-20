package com.vivek.commerce.product;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.vivek.commerce.product.category.CategoryDTO;
import com.vivek.commerce.product.review.ReviewRatingDTO;

import lombok.Data;

@Data
public class ProductResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3532532523532L;

    private Long id;

    private String title;

    private String description;

    private double price;

    private double discountPrice;

    private int discountPercent;

    private int quantity;

    private String brand;

    private String imageUrl;

    private Set<SizeDTO> sizes = new HashSet<>();

    private List<ReviewRatingDTO> reviews = new ArrayList<>();

    private int numRating;

    private CategoryDTO category;

    private LocalDate createdTime;

}