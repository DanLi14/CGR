package com.qa.cgr.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewAllGamesPage {
	
	public String URL = "http://127.0.0.1:5500/src/main/resources/static/html/viewgames.html";

	@FindBy(xpath = "/html/body/div/div/h2")
	private WebElement viewAllGamesHeader;
	
	public String checkHeader() {
		return viewAllGamesHeader.getText();
	}

}
