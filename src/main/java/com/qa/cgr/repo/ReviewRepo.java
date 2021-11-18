package com.qa.cgr.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.cgr.domain.Review;
import com.qa.cgr.dto.ReviewWithGameTitleDTO;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
	
	// custom query using DTO
	@Query(value = "SELECT * FROM review WHERE game.title=?1", nativeQuery = true)
	Optional<List<Review>> findAllReviewsByGame(String title);
	
	// Custom query selecting review(s) by username
	@Query(value = "SELECT * FROM review WHERE username=?1", nativeQuery = true)
	Optional<List<Review>> findAllReviewsByUsername(String username);

}
