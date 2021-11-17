package com.qa.cgr.dto;

public class ReviewWithGameTitleDTO {
	
	private Long id; 
	private String username;  
	private int score;
	private String review;
	private String title;
	
	// Full constructor
	public ReviewWithGameTitleDTO(Long id, String username, int score, String review, String title) {
		super();
		this.id = id;
		this.username = username;
		this.score = score;
		this.review = review;
		this.title = title;
	}

	// Empty constructor
	public ReviewWithGameTitleDTO() {
		super();
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
