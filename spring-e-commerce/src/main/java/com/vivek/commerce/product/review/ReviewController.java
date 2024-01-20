package com.vivek.commerce.product.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product/review")
@Validated
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping
    public List<ReviewRatingDTO> getAllReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewRatingDTO> getReviewById(@PathVariable Long id) {
	ReviewRatingDTO reviewDTO = reviewService.getReviewById(id);
        if (reviewDTO != null) {
            return ResponseEntity.ok(reviewDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReviewRatingDTO> createReview(@Valid @RequestBody ReviewRatingDTO reviewDTO) {
	ReviewRatingDTO createdReviewDTO = reviewService.createReview(reviewDTO);
        return new ResponseEntity<>(createdReviewDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewByProductId(@PathVariable Long id) {
        reviewService.deleteReviewsByProductId(id);
        return ResponseEntity.noContent().build();
    }
}