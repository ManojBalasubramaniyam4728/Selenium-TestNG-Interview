package com.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostAJob_Design_Page {
	// WedDriver invoking
	WebDriver driver;

	// Giving life to driver by Constracor
	public PostAJob_Design_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

// **************************************************************************************************************************************
	// Storing Elements By FindBy anotation
	@FindBy(xpath = "//p[text()='Design']")
	WebElement DesignText;
	
	@FindBy(xpath = "//textarea[@id='job_description']")
	WebElement JobDescriptionTextarea;

	@FindBy(xpath = "//button[@title='Attach file']")
	WebElement AttachfileButton;
	
	@FindBy(xpath = "//button/span[text()='Continue']")
	WebElement ContinueButton;
	
	
	// User Defined Method To Above Elements
	public WebElement DesignText() {
		return DesignText;
	}
	
	public WebElement JobDescriptionTextarea() {
		return JobDescriptionTextarea;
	}
	
	public WebElement AttachfileButton() {
		return AttachfileButton;
	}

	public WebElement ContinueButton() {
		return ContinueButton;
	}

	// Dynamic Element
	public WebElement WhereInTheDesignProcessAreYouradioButton(String WhereInTheDesignProcessAreYou) {
		By WhereInTheDesignProcessAreYouOptions = By.xpath("//span[text()='"+WhereInTheDesignProcessAreYou+"']/..//span");
		return driver.findElement(WhereInTheDesignProcessAreYouOptions);
	}
}
