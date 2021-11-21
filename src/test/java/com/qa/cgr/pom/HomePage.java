package com.qa.cgr.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	// URL
	public String URL = "http://127.0.0.1:5500/src/main/resources/static/index.html";

	// DOM Selectors
	@FindBy(xpath = "/html/body/section[2]/article/p")
	private WebElement homePageContent;

	@FindBy(xpath = "/html/body/nav/div/div/ul/li[2]/a")
	private WebElement gameNav;

	@FindBy(xpath = "/html/body/nav/div/div/ul/li[2]/ul/li[1]/a")
	private WebElement viewAllGames;

	@FindBy(xpath = "/html/body/nav/div/div/ul/li[2]/ul/li[2]/a")
	private WebElement searchForAGame;
	
	@FindBy(xpath = "/html/body/nav/div/div/ul/li[2]/ul/li[3]/a")
	private WebElement addAGame;

	// Methods
	public String checkContent() {
		return homePageContent.getText();
	}

	public void clickGameNav() {
		gameNav.click();

	}

	public void clickViewAllGames() {
		viewAllGames.click();
	}

	public void clickSearchGame() {
		searchForAGame.click();
	}
	
	public void clickAddGame() {
		addAGame.click();
	}

}
