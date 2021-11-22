package com.qa.cgr.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cgr.domain.Review;
import com.qa.cgr.dto.ReviewWithGameTitleDTO;
import com.qa.cgr.service.ReviewService;

@RestController
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {

	private ReviewService service;

	// constructor injection
	public ReviewController(ReviewService service) {
		this.service = service;
	}

	// Create
	@PostMapping("/create")
	public ResponseEntity<Review> create(@RequestBody Review review) {
		return new ResponseEntity<Review>(this.service.create(review), HttpStatus.CREATED);
	}

	// Read Test only
	@GetMapping("/getAllGames")
	public ResponseEntity<List<Review>> getAllGames() {
		return new ResponseEntity<List<Review>>(this.service.getAllGames(), HttpStatus.OK);
	}

	// Read All DTO
	@GetMapping("/getAll")
	public ResponseEntity<List<ReviewWithGameTitleDTO>> getAll() {
		return new ResponseEntity<List<ReviewWithGameTitleDTO>>(this.service.getAll(), HttpStatus.OK);
	}

	// Custom query - read all by username
	@GetMapping("/getAllReviewsByUsername/{username}")
	public ResponseEntity<List<Review>> getAllReviewsByUsername(@PathVariable String username) {
		return new ResponseEntity<List<Review>>(this.service.getAllReviewsByUsername(username), HttpStatus.OK);
	}

	// Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Review> update(@PathVariable Long id, @RequestBody Review review) {
		return new ResponseEntity<Review>(this.service.update(id, review), HttpStatus.ACCEPTED);
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Review> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<Review>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
