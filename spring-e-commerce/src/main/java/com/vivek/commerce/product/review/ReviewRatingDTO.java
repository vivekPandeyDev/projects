package com.vivek.commerce.product.review;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ReviewRatingDTO  implements Serializable {

    @Serial
    private static final long serialVersionUID = 3532532532532L;

    private Long id; 

    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotNull(message = "Product ID cannot be null")
    @Positive(message = "Product ID must be positive")
    private Long productId;

    @NotNull(message = "Rating value cannot be null")
    @Min(value = 1, message = "Rating value must be at least 1")
    @Max(value = 5, message = "Rating value must be at most 5")
    private Integer ratingValue;

    @NotNull(message = "Review content cannot be null")
    private String reviewContent;

}
