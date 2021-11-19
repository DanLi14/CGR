package com.qa.cgr.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.cgr.domain.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
	
	// Custom query selecting game by title
	@Query(value = "SELECT * FROM game WHERE title=?1", nativeQuery = true)
	Optional<Game>findGameByTitle(String title);

}
