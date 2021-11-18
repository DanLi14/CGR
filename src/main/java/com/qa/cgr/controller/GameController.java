package com.qa.cgr.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cgr.domain.Game;
import com.qa.cgr.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

	private GameService service;

	// Constructor Injection
	public GameController(GameService service) {
		this.service = service;
	}

	// Create
	@PostMapping("/create")
	public ResponseEntity<Game> create(@RequestBody Game game) {
		return new ResponseEntity<Game>(this.service.create(game), HttpStatus.CREATED);
	}

	// Read
	@GetMapping("/getAll")
	public ResponseEntity<List<Game>> getAll() {
		return new ResponseEntity<List<Game>>(this.service.getAll(), HttpStatus.OK);
	}
	
	// Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game) {
		return new ResponseEntity<Game>(this.service.update(id, game), HttpStatus.ACCEPTED);
	}
	
	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Game> delete(@PathVariable Long id){
		return this.service.delete(id) ? new ResponseEntity<Game>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<Game>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
