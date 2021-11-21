package com.qa.cgr.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddAGamePage {
	
	public String URL = "http://127.0.0.1:5500/src/main/resources/static/html/addGame.html";

	@FindBy(xpath = "/html/body/div/h2")
	private WebElement addAGameHeader;
	
	public String checkHeader() {
		return addAGameHeader.getText();
	}

}
