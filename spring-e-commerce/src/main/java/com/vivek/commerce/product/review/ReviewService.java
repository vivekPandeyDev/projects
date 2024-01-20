package com.vivek.commerce.product.review;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ReviewService {
    List<ReviewRatingDTO> getReviews();
    
    ReviewRatingDTO getReviewById(@NotNull Long id);
    
    ReviewRatingDTO createReview(@Valid ReviewRatingDTO reviewRatingDto );
    
    ReviewRatingDTO updateReview(@NotNull Long reviewId, @Valid ReviewRatingDTO reviewRatingDto );
    
    void deleteReviewsByProductId(@NotNull Long id);
}
