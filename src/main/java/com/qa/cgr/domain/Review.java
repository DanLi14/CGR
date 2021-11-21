package com.qa.cgr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	@Min(1)
	@Max(10)
	private int score;

	// increase length from default 255
	@Column(length = 1600)
	private String review;
	
	@ManyToOne 
	private Game game;
	
	// Constructor for Spring
	public Review(String username, @Min(1) @Max(10) int score, String review) {
		super();
		this.username = username;
		this.score = score;
		this.review = review;
	}

	// Constructor for testing w/o id
	public Review(String username, @Min(1) @Max(10) int score, String review, Game game) {
		super();
		this.username = username;
		this.score = score;
		this.review = review;
		this.game = game;
	}
	
	// Constructor for testing w/o id
		public Review(long id, Game game, String review, @Min(1) @Max(10) int score, String username) {
			super();
			this.id = id;
			this.username = username;
			this.score = score;
			this.review = review;
			this.game = game;
		}
	
	// Constructor for testing with id
	public Review(long id, String username, @Min(1) @Max(10) int score, String review) {
		super();
		this.id = id;
		this.username = username;
		this.score = score;
		this.review = review;
	}
	
}
