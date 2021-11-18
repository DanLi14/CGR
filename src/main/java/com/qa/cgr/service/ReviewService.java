package com.qa.cgr.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qa.cgr.domain.Review;
import com.qa.cgr.dto.ReviewWithGameTitleDTO;
import com.qa.cgr.exceptions.GameNotFoundException;
import com.qa.cgr.exceptions.UsernameNotFoundException;
import com.qa.cgr.repo.ReviewRepo;

@Service
public class ReviewService {

	private ReviewRepo repo;

	public ReviewService(ReviewRepo repo) {
		this.repo = repo;
	}

	// mapping to ReviewWithGameTitleDTO

	private ReviewWithGameTitleDTO mapToDTO(Review review) {
		ReviewWithGameTitleDTO dto = new ReviewWithGameTitleDTO();

		dto.setId(review.getId());
		dto.setUsername(review.getUsername());
		dto.setScore(review.getScore());
		dto.setReview(review.getReview());
		dto.setTitle(review.getGame().getTitle());

		return dto;
	}

	// Create
	public Review create(Review review) {
		return this.repo.saveAndFlush(review);
	}

	// Read All - for testing and MVP purposes only
	public List<Review> getAllTest() {
		return this.repo.findAll();
	}

	// Read All - includes game title associated with each review
	public List<ReviewWithGameTitleDTO> getAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// Custom query - read all reviews by username
	public List<Review> getAllReviewsByUsername(String username) {
		return this.repo.findAllReviewsByUsername(username).orElseThrow(UsernameNotFoundException::new);
	}

	// Custom query - read all reviews by game title.
	public List<Review> getAllReviewsByTitle(String title) {
		return this.repo.findAllReviewsByGame(title).orElseThrow(GameNotFoundException::new);
	}

	// Update
	public Review update(Long id, Review review) {
		Review existing = this.repo.findById(id).get();
		existing.setUsername(review.getUsername());
		existing.setScore(review.getScore());
		existing.setReview(review.getReview());
		return this.repo.saveAndFlush(existing);
	}

	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
