package com.qa.cgr.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String title;
	
	// increase length from default 255
	@Column(length = 500)
	private String descript;
	
	@Column(nullable = false)
	private boolean pc;
	
	@Column(nullable = false)
	private boolean playstation;
	
	@Column(nullable = false)
	private boolean xbox;
	
	@Column(nullable = false)
	private boolean nintendo;
	
	// increase length from default 255 for those edge cases where image link is really long.
	@Column(length = 600)
	private String image;
	
	@JsonIgnore
	@OneToMany(mappedBy="game")
	private List<Review>reviews;

	// Constructor for Spring
	public Game(String title, String descript, boolean pc, boolean playstation, boolean xbox, boolean nintendo,
			String image) {
		super();
		this.title = title;
		this.descript = descript;
		this.pc = pc;
		this.playstation = playstation;
		this.xbox = xbox;
		this.nintendo = nintendo;
		this.image = image;
	}
	
}
