package com.qa.cgr.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cgr.domain.Game;
import com.qa.cgr.domain.Review;
import com.qa.cgr.dto.ReviewWithGameTitleDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // loads context and boots up on different port
@AutoConfigureMockMvc // tells spring to setup the mockmvc object
@ActiveProfiles("test")
// runs the sql files before each test method
@Sql(scripts = {"classpath:table-schema.sql", "classpath:review-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ReviewControllerIntegrationTest {
	
	@Autowired // tells spring to inject in the mockmvc object
	private MockMvc mvc; // library that performs the requests

	@Autowired
	private ObjectMapper mapper; // the actual object spring uses to convert Java to JSON and vice versa.

	@Test
	void testCreate() throws Exception {
		// Arrange
		Game existingGame = new Game(1l, "Persona 5", "Persona 5 is a 2016 role-playing video game developed by Atlus. It is the sixth installment in the Persona series, which is part of the larger Megami Tensei franchise.", false, true, false, false, "https://www.nme.com/wp-content/uploads/2021/07/Persona-5-Royal-Best-PS4-Games@2000x1270-696x442.jpg");
		Review newReview = new Review(2l, existingGame, "This game stole my heart", 8, "Kelvin");
		String newReviewAsJSON = this.mapper.writeValueAsString(newReview);
		ResultMatcher checkstatus = status().isCreated();
		Review newReviewSaved = new Review(2l, "This game stole my heart", 8, "Kelvin");
		String newReviewSavedAsJSON = this.mapper.writeValueAsString(newReviewSaved);
		
		// Act
		RequestBuilder request = post("/reviews/create").contentType(MediaType.APPLICATION_JSON).content(newReviewAsJSON);
		ResultMatcher checkBody = content().json(newReviewSavedAsJSON);
		
		//Assertion
		this.mvc.perform(request).andExpect(checkstatus).andExpect(checkBody);
	}
	
	@Test
	void testReadAll() throws Exception {
		// Arrange
		Review existingReview = new Review(1l, "Zack", 10, "I really did not think I would like this game, but I am super happy I gave it a chance");
		String existingReviewAsJSON = this.mapper.writeValueAsString(List.of(existingReview));
		ResultMatcher checkstatus = status().isOk();
		
		// Act
		RequestBuilder request = get("/reviews/getAllGames");
		ResultMatcher checkBody = content().json(existingReviewAsJSON);
		
		// Assertion
		this.mvc.perform(request).andExpect(checkstatus).andExpect(checkBody);
		
	}
	
	@Test
	void testReadAllDTO() throws Exception {
		//Arrange
		ReviewWithGameTitleDTO existingReviewWithGameTitle = new ReviewWithGameTitleDTO(1l, "Zack", 10, "I really did not think I would like this game, but I am super happy I gave it a chance", "Persona 5");
		String existingReviewWithGameTitleAsJSON = this.mapper.writeValueAsString(List.of(existingReviewWithGameTitle));
		ResultMatcher checkstatus = status().isOk();
		
		// Act
		RequestBuilder request = get("/reviews/getAll");
		ResultMatcher checkBody = content().json(existingReviewWithGameTitleAsJSON);
		
		// Assertion
		this.mvc.perform(request).andExpect(checkstatus).andExpect(checkBody);
	}
	
	@Test
	void testReadAllByUsername() throws Exception {
		Review existingReview = new Review(1l, "Zack", 10, "I really did not think I would like this game, but I am super happy I gave it a chance");
		String existingReviewAsJSON = this.mapper.writeValueAsString(List.of(existingReview));
		ResultMatcher checkstatus = status().isOk();
		
		// Act
		RequestBuilder request = get("/reviews/getAllReviewsByUsername/" + existingReview.getUsername());
		ResultMatcher checkBody = content().json(existingReviewAsJSON);
		
		// Assertion
		this.mvc.perform(request).andExpect(checkstatus).andExpect(checkBody);
		
	}
	
	@Test
	void testUpdate() throws Exception {
		// Arrange
		Review updatedReview = new Review(1l, "Zack", 5, "In hindsight I thought it was meh.");
		String updatedReviewAsJSON = this.mapper.writeValueAsString(updatedReview);
		ResultMatcher checkStatus = status().isAccepted();
		Review updatedReviewSaved = new Review(1l, "Zack", 5, "In hindsight I thought it was meh.");
		String updatedReviewSavedAsJSON = this.mapper.writeValueAsString(updatedReviewSaved);
		
		//Act
		RequestBuilder request = put("/reviews/update/1").contentType(MediaType.APPLICATION_JSON).content(updatedReviewAsJSON);
		ResultMatcher checkBody = content().json(updatedReviewSavedAsJSON);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		// Arrange
		ResultMatcher checkStatus = status().isNoContent();
		
		// Act
		RequestBuilder request = delete("/reviews/delete/1");
		
		//Assertion
		this.mvc.perform(request).andExpect(checkStatus); 
	}
	

}
