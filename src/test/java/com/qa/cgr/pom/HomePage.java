package com.qa.cgr.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	public String URL = "http://127.0.0.1:5500/src/main/resources/static/index.html";

	@FindBy(xpath = "/html/body/section[2]/article/p")
	private WebElement homePageContent;
	
	public String checkContent() {
		return homePageContent.getText();
	}
}
