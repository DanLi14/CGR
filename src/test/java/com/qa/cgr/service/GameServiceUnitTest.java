package com.qa.cgr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.cgr.domain.Game;
import com.qa.cgr.repo.GameRepo;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceUnitTest {

	@InjectMocks
	private GameService gameService;

	@Mock
	private GameRepo gameRepo;

	@Test
	public void createTest() {
		Game input = new Game("fifa 22",
				"FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.",
				true, true, true, true,
				"http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		Game output = new Game(1l, "fifa 22",
				"FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.",
				true, true, true, true,
				"http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");

		Mockito.when(this.gameRepo.saveAndFlush(input)).thenReturn(output);

		Assertions.assertEquals(output, this.gameService.create(input));

		Mockito.verify(this.gameRepo, Mockito.times(1)).saveAndFlush(input);

	}

	@Test
	public void getAllTest() {
		List<Game> games = new ArrayList<>();
		Game game = new Game("fifa 22",
				"FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.",
				true, true, true, true,
				"http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		games.add(game);

		Mockito.when(this.gameRepo.findAll()).thenReturn(games);

		Assertions.assertEquals(games, this.gameService.getAll());

		Mockito.verify(this.gameRepo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void updateTest() {
		String inputTitle = "fifa 22";
		Optional<Game> optionalGame = Optional.of(new Game(1l, "fifa 22",
				"FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.",
				true, true, true, true,
				"http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg"));
		Game updatedGame = new Game(1l, "fifa 21",
				"FIFA 21 is an association football simulation video game published by Electronic Arts as part of the FIFA series.",
				true, true, true, true,
				"http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");

		Mockito.when(this.gameRepo.findGameByTitle(inputTitle)).thenReturn(optionalGame);
		Mockito.when(this.gameRepo.saveAndFlush(updatedGame)).thenReturn(updatedGame);

		Assertions.assertEquals(updatedGame, this.gameService.update(inputTitle, updatedGame));

		Mockito.verify(this.gameRepo, Mockito.times(1)).findGameByTitle(inputTitle);
		Mockito.verify(this.gameRepo, Mockito.times(1)).saveAndFlush(updatedGame);
	}
	
	@Test
	public void deleteTest() {
		Long inputId = 1L;
		boolean booF = false;
		boolean booT = true;

		Mockito.when(this.gameRepo.existsById(inputId)).thenReturn(booF);

		Assertions.assertEquals(booT, this.gameService.delete(inputId));
		
		Mockito.verify(this.gameRepo, Mockito.times(1)).deleteById(inputId);
		Mockito.verify(this.gameRepo, Mockito.times(1)).existsById(inputId);

	}
	
}
