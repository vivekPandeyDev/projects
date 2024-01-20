package com.vivek.commerce.product.review;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.vivek.commerce.exception.NotFoundException;
import com.vivek.commerce.exception.ServiceException;
import com.vivek.commerce.product.Product;
import com.vivek.commerce.product.ProductRepository;
import com.vivek.commerce.user.CommerceUser;
import com.vivek.commerce.user.CommerceUserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final CommerceUserRepository userRepository;
	private final ModelMapper modelMapper;

    @Override
    public ReviewRatingDTO getReviewById(@NotNull Long reviewId) {
	return reviewRepository.findById(reviewId)
		.map(ReviewServiceImpl::convertToDto)
		.orElseThrow(() -> new NotFoundException("No review found with Id: "+reviewId));
		
    }

    @Override
    public ReviewRatingDTO createReview(@Valid ReviewRatingDTO reviewRatingDTO) {

	// Check if product exists and user exists
	Product product = productRepository.findById(reviewRatingDTO.getProductId()).orElseThrow(
		() -> new NotFoundException("Product not found with ID: " + reviewRatingDTO.getProductId()));

	CommerceUser user = userRepository.findById(reviewRatingDTO.getUserId())
		.orElseThrow(() -> new NotFoundException("User not found with ID: " + reviewRatingDTO.getUserId()));

	// check if user already review that product
	if(reviewRepository.existsByUserIdAndProductId(user.getUserId(), product.getId())) {
	    throw new ServiceException("Cannot create new review, as review already exists for the product","REVIEW ALREADY EXISTS");
	}
	// Both product and user exist, manually create ReviewRatingDTO
	Review review = convertToEntity(reviewRatingDTO, product, user);
	review = reviewRepository.save(review);
	return convertToDto(review);
    }

    @Override
    public ReviewRatingDTO updateReview(@NotNull Long reviewId, @Valid ReviewRatingDTO reviewRatingDTO) {
	return reviewRepository.findById(reviewId).map(existingReview -> {
	    modelMapper.map(reviewRatingDTO, existingReview);
	    Review updatedReview = reviewRepository.save(existingReview);
	    return modelMapper.map(updatedReview, ReviewRatingDTO.class);
	}).orElse(null);
    }


    @Override
    public void deleteReviewsByProductId(@NotNull Long productId) {
	reviewRepository.deleteReviewsByProductId(productId);
    }

    @Override
    public List<ReviewRatingDTO> getReviews() {
	return reviewRepository.findAll().stream().map(ReviewServiceImpl::convertToDto).toList();
    }


    private Review convertToEntity(ReviewRatingDTO reviewRatingDTO, Product product, CommerceUser user) {
	Review review = new Review();
	review.setProduct(product);
	review.setUser(user);
	review.setRatingValue(reviewRatingDTO.getRatingValue());
	review.setReviewContent(reviewRatingDTO.getReviewContent());
	return review;
    }

    public static ReviewRatingDTO convertToDto(Review review) {
	ReviewRatingDTO dto = new ReviewRatingDTO();
	dto.setId(review.getReviewId());
	dto.setProductId(review.getProduct().getId());
	dto.setRatingValue(review.getRatingValue());
	dto.setReviewContent(review.getReviewContent());
	dto.setUserId(review.getUser().getUserId());
	return dto;
    }
}
