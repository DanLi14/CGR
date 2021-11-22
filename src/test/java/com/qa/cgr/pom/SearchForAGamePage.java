package com.qa.cgr.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchForAGamePage {
	
	public String URL = "http://127.0.0.1:5500/src/main/resources/static/html/searchGame.html";

	@FindBy(xpath = "/html/body/div/h2")
	private WebElement searchForAGameHeader;
	
	public String checkHeader() {
		return searchForAGameHeader.getText();
	}

}
