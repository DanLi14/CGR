package com.qa.cgr.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateAGamePage {
	
	public String URL = "http://127.0.0.1:5500/src/main/resources/static/html/updateGame.html";

	@FindBy(xpath = "/html/body/div/form/div/div[1]/label")
	private WebElement updateGameLabel;
	
	public String checkLabel() {
		return updateGameLabel.getText();
	}

}
