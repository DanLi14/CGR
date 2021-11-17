package com.qa.cgr.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.cgr.domain.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
	
	// custom 'join' query using DTO 	
	@Query(value = "SELECT * FROM game WHERE title=?1", nativeQuery = true)
	Optional<List<Game>> findAllByTitle(String title);
	
	// custom 'join' query using DTO 	
	@Query(value = "SELECT AVG(score) FROM game WHERE title=?1", nativeQuery = true)
	Optional<List<Game>> findAvgReviewScoreByTitle(String title);
}
