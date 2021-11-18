package com.qa.cgr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.cgr.domain.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
	

}
