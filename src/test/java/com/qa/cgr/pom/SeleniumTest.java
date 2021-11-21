package com.qa.cgr.pom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class SeleniumTest {

	private WebDriver driver;
	
	@BeforeEach
	void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(); // our WebDriver is being specific as a chrome driver.
		this.driver.manage().window().setSize(new Dimension(1366, 768));
	}
		
	@Test
	void Homepage() {
		// Arrange
		String Homecontent = "Community Game Reviews is a community only review website";
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		
		//Act
		driver.get(homePage.URL);
		
		// Assertion
		Assertions.assertTrue(homePage.checkContent().contains(Homecontent));
		
	}
	
	@Test
	void ViewAllGamesPage() {
		// Arrange
		String header = "Game Library";
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ViewAllGamesPage viewAllGamesPage = PageFactory.initElements(driver, ViewAllGamesPage.class);
		
		// Act
		driver.get(homePage.URL);
		homePage.clickGameNav();
		homePage.clickViewAllGames();
		
		// Assert
		Assertions.assertEquals(header, viewAllGamesPage.checkHeader());
	}
	
	@AfterEach
	void teardown() {
		driver.close();
	}

}