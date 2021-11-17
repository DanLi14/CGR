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

	@Column()
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
	
}
