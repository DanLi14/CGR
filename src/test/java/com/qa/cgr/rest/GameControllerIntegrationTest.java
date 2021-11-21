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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // loads context and boots up on different port
@AutoConfigureMockMvc // tells spring to setup the mockmvc object
@ActiveProfiles("test")
// runs the sql files before each test method
@Sql(scripts = {"classpath:table-schema.sql", "classpath:game-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class GameControllerIntegrationTest {
	
	@Autowired 
	private MockMvc mvc; // library that performs the requests

	@Autowired
	private ObjectMapper mapper; // the actual object spring uses to convert Java to JSON and vice versa.
	
	@Test
	void testCreate() throws Exception {
		// Arrange
		Game newGame = new Game("fifa 22", "FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.", true, true, true, true, "http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		String newGameAsJSON = this.mapper.writeValueAsString(newGame);
		ResultMatcher checkstatus = status().isCreated();
		Game newGameSaved = new Game(2l, "fifa 22", "FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.", true, true, true, true, "http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		String newGameSavedAsJSON = this.mapper.writeValueAsString(newGameSaved);
		
		// Act
		RequestBuilder request = post("/game/create").contentType(MediaType.APPLICATION_JSON).content(newGameAsJSON);
		ResultMatcher checkBody = content().json(newGameSavedAsJSON);
		
		//Assertion
		this.mvc.perform(request).andExpect(checkstatus).andExpect(checkBody);
	}
	
	@Test
	void testReadAll() throws Exception {
		// Arrange
		Game existingGame = new Game(1l, "Persona 5", "Persona 5 is a 2016 role-playing video game developed by Atlus. It is the sixth installment in the Persona series, which is part of the larger Megami Tensei franchise.", false, true, false, false, "https://www.nme.com/wp-content/uploads/2021/07/Persona-5-Royal-Best-PS4-Games@2000x1270-696x442.jpg");
		String existingGameAsJSON = this.mapper.writeValueAsString(List.of(existingGame));
		ResultMatcher checkstatus = status().isOk();
		
		// Act
		RequestBuilder request = get("/game/getAll");
		ResultMatcher checkBody = content().json(existingGameAsJSON);
		
		// Assertion
		this.mvc.perform(request).andExpect(checkstatus).andExpect(checkBody);
	}
	
	@Test
	void testUpdate() throws Exception {
		// Arrange
		Game updatedGame = new Game("fifa 22", "FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.", true, true, true, true, "http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		String updatedGameAsJSON = this.mapper.writeValueAsString(updatedGame);
		ResultMatcher checkStatus = status().isAccepted();
		Game updatedGameSaved = new Game(1l, "fifa 22", "FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.", true, true, true, true, "http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		String updatedGameSavedAsJSON = this.mapper.writeValueAsString(updatedGameSaved);
		
		//Act
		RequestBuilder request = put("/game/update/Persona 5").contentType(MediaType.APPLICATION_JSON).content(updatedGameAsJSON);
		ResultMatcher checkBody = content().json(updatedGameSavedAsJSON);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		// Arrange
		ResultMatcher checkStatus = status().isNoContent();
		
		// Act
		RequestBuilder request = delete("/game/delete/1");
		
		//Assertion
		this.mvc.perform(request).andExpect(checkStatus); 
	}
	
}
