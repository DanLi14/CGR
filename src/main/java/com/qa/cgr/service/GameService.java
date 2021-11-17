package com.qa.cgr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.cgr.domain.Game;
import com.qa.cgr.repo.GameRepo;

@Service
public class GameService {

	private GameRepo repo;

	public GameService(GameRepo repo) {
		this.repo = repo;
	}

	// Create
	public Game create(Game game) {
		return this.repo.saveAndFlush(game);
	}

	// Read All - for testing and MVP purposes only
	public List<Game> getAll() {
		return this.repo.findAll();
	}

	// Update
	public Game update(Long id, Game game) {
		Game existing = this.repo.findById(id).get();
		existing.setTitle(game.getTitle());
		existing.setDescript(game.getDescript());
		existing.setPc(game.isPc());
		existing.setPlaystation(game.isPlaystation());
		existing.setXbox(game.isXbox());
		existing.setNintendo(game.isNintendo());
		existing.setImage(game.getImage());
		return this.repo.saveAndFlush(existing);
	}

	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
