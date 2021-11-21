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

		// Act
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

	@Test
	void SearchForAGamePage() {
		// Arrange
		String header = "Search game by name";
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		SearchForAGamePage searchForAGamePage = PageFactory.initElements(driver, SearchForAGamePage.class);

		// Act
		driver.get(homePage.URL);
		homePage.clickGameNav();
		homePage.clickSearchGame();
		;

		// Assert
		Assertions.assertEquals(header, searchForAGamePage.checkHeader());
	}

	@Test
	void AddAGamePage() {
		// Arrange
		String header = "Add a game to the Library";
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		AddAGamePage addAGamePage = PageFactory.initElements(driver, AddAGamePage.class);

		// Act
		driver.get(homePage.URL);
		homePage.clickGameNav();
		homePage.clickAddGame();

		// Assert
		Assertions.assertEquals(header, addAGamePage.checkHeader());
	}
	
	@Test
	void UpdateAGamePage() {
		// Arrange
		String label = "Existing game";
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		UpdateAGamePage updateAGamePage = PageFactory.initElements(driver, UpdateAGamePage.class);

		// Act
		driver.get(homePage.URL);
		homePage.clickGameNav();
		homePage.clickUpdateGame();

		// Assert
		Assertions.assertEquals(label, updateAGamePage.checkLabel());
	}
	
	@Test
	void DeleteAGamePage() {
		// Arrange
		String header = "Delete a game from the library";
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		DeleteAGamePage deleteAGamePage = PageFactory.initElements(driver, DeleteAGamePage.class);

		// Act
		driver.get(homePage.URL);
		homePage.clickGameNav();
		homePage.clickDeleteGame();

		// Assert
		Assertions.assertEquals(header, deleteAGamePage.checkHeader());
	}
	
	@Test
	void SearchForReviewsByGamePage() {
		// Arrange
		String header = "Search reviews by game";
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		SearchReviewsByGamePage searchReviewsByGamePage = PageFactory.initElements(driver, SearchReviewsByGamePage.class);

		// Act
		driver.get(homePage.URL);
		homePage.clickReviewNav();
		homePage.clickSearchReviewsByGame();

		// Assert
		Assertions.assertEquals(header, searchReviewsByGamePage.checkHeader());
	}

	@AfterEach
	void teardown() {
		driver.close();
	}

}