package com.qa.cgr.service;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
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
		Game input = new Game("fifa 22", "FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.", true, true, true, true, "http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		Game output = new Game(1l, "fifa 22", "FIFA 22 is an association football simulation video game published by Electronic Arts as part of the FIFA series.", true, true, true, true, "http://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_FIFA22NintendoSwitchLegacyEdition_image1280w.jpg");
		
		Mockito.when(this.gameRepo.saveAndFlush(input)).thenReturn(output); 
		
		Assertions.assertEquals(output, this.gameService.create(input));
		
		Mockito.verify(this.gameRepo, Mockito.times(1)).saveAndFlush(input);
		
	} 

}
