package com.qa.cgr.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cgr.controller.GameController;
import com.qa.cgr.domain.Game;
import com.qa.cgr.service.GameService;

@WebMvcTest
class GameControllerUnitTest {
	
	@Autowired // tells spring to inject in the mockmvc object
	private MockMvc mvc; // library that performs the requests
	
	@Autowired
	private ObjectMapper mapper; // the actual object spring uses to convert Java to JSON and vice versa.
	
	@MockBean
	private GameService gameService;
	
	@Test
	void testCreate() throws Exception {
		// Arrange
		Game newGame = new Game(1l, "fifa 22", "FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.", true, true, true, true, "http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		String newGameAsJSON = this.mapper.writeValueAsString(newGame);
		ResultMatcher checkstatus = status().isCreated();
		Game newGameSaved = new Game(1l, "fifa 22", "FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.", true, true, true, true, "http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		String newGameSavedAsJSON = this.mapper.writeValueAsString(newGameSaved);
		
		// Act
		RequestBuilder request = post("/game/create").contentType(MediaType.APPLICATION_JSON).content(newGameAsJSON);
		ResultMatcher checkBody = content().json(newGameSavedAsJSON);
		
		//Assertion
		Mockito.when(this.gameService.create(newGame)).thenReturn(newGame);
		this.mvc.perform(request).andExpect(checkstatus).andExpect(checkBody);
	}
	
	@Test
	void testReadAll() throws Exception {
		// Arrange
		Game existingGame = new Game(1l, "Persona 5", "Persona 5 is a 2016 role-playing video game developed by Atlus. It is the sixth installment in the Persona series, which is part of the larger Megami Tensei franchise.", false, true, false, false, "https://www.nme.com/wp-content/uploads/2021/07/Persona-5-Royal-Best-PS4-Games@2000x1270-696x442.jpg");
		List<Game> games = new ArrayList<>();
		games.add(existingGame);
		String existingGameAsJSON = this.mapper.writeValueAsString(List.of(existingGame));
		ResultMatcher checkstatus = status().isOk();
		
		// Act
		RequestBuilder request = get("/game/getAll");
		ResultMatcher checkBody = content().json(existingGameAsJSON);
		
		// Assertion
		Mockito.when(this.gameService.getAll()).thenReturn(games);
		this.mvc.perform(request).andExpect(checkstatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		// Arrange
		long id = 1L;
		boolean booT = true;
		ResultMatcher checkStatus = status().isNoContent();
		
		// Act
		RequestBuilder request = delete("/game/delete/1");
		
		//Assertion
		this.mvc.perform(request).andExpect(checkStatus);
		Mockito.when(this.gameService.delete(id)).thenReturn(booT);
	}
	
}
