package com.vivek.commerce.product.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRatingRepository extends JpaRepository<Review, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.product.id = :productId")
    void deleteAllReviewsByProductId(Long productId);
}
