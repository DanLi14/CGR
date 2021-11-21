package com.qa.cgr.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchReviewsByGamePage {
	
	public String URL = "http://127.0.0.1:5500/src/main/resources/static/html/reviewsbygame.html";

	@FindBy(xpath = "/html/body/div/h2")
	private WebElement searchReviewsByGameHeader;
	
	public String checkHeader() {
		return searchReviewsByGameHeader.getText();
	}

}
