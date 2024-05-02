package com.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostAJob_ProjectDetail_Page {
	// WedDriver invoking
	WebDriver driver;

	// Giving life to driver by Constracor
	public PostAJob_ProjectDetail_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

// **************************************************************************************************************************************
	// Storing Elements By FindBy anotation
	@FindBy(xpath = "//button[text()='I Agree.']")
	WebElement accepetCookiesButton;
	
	@FindBy(xpath = "//div[@class='project__header']//p[text()='Post a job']")
	WebElement PostAJobText;

	@FindBy(xpath = "(//div[@id='ScrollToTopProjectForm']/..//p)[2]")
	WebElement LetsStartWiththeBasicsText;

	@FindBy(xpath = "//input[@id='project_name']")
	WebElement GiveYourProjectANameTextField;

	@FindBy(xpath = "//input[@id='width']")
	WebElement WidthTextfield;

	@FindBy(xpath = "//input[@id='height']")
	WebElement HeightTextfield;
	
	@FindBy(xpath = "//input[@placeholder='Search your location...']")
	WebElement LocationOfTheProject;

	@FindBy(xpath = "//button/span[text()='Continue']")
	WebElement ContinueButton;
	

	// User Defined Method To Above Elements
	public WebElement accepetCookiesButton() {
		return accepetCookiesButton;
	}
	
	public WebElement PostAJobText() {
		return PostAJobText;
	}
	
	public WebElement LetsStartWiththeBasicsText() {
		return LetsStartWiththeBasicsText;
	}
	
	public WebElement GiveYourProjectANameTextField() {
		return GiveYourProjectANameTextField;
	}
	
	public WebElement WidthTextfield() {
		return WidthTextfield;
	}
	
	public WebElement LocationOfTheProject() {
		return LocationOfTheProject;
	}
	
	public WebElement HeightTextfield() {
		return HeightTextfield;
	}
	
	public WebElement ContinueButton() {
		return ContinueButton;
	}
	
	// Dynamic Element
	public WebElement WhatKindOfArtAreYouRequestingOptions(String RequestingName) {
		By WhatKindOfArtAreYouRequesting = By.xpath("//span[text()='" +RequestingName
				+ "']");
		return driver.findElement(WhatKindOfArtAreYouRequesting);
	}

	public WebElement WhereWillTheMuralGoCheckBox(String MuralGo) {
		By WhereWillTheMuralGo = By.xpath("//span[text()='" + MuralGo + "']/..//input[@type=\"checkbox\"]");
		return driver.findElement(WhereWillTheMuralGo);
	}

	public WebElement MeasureByOption(String MeasureIn) {
		By MeasureBy = By.xpath("//span[text()='" + MeasureIn + "']");
		return driver.findElement(MeasureBy);
	}

	public WebElement TypeOfPropertyOption(String PropertyType) {
		By TypeOfProperty = By.xpath("//input[@value='" + PropertyType + "']/..");
		return driver.findElement(TypeOfProperty);
	}
}
