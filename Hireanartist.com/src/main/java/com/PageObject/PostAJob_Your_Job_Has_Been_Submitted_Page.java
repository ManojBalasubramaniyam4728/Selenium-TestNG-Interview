package com.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostAJob_Your_Job_Has_Been_Submitted_Page {
	// WedDriver invoking
	WebDriver driver;

	// Giving life to driver by Constracor
	public PostAJob_Your_Job_Has_Been_Submitted_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

// **************************************************************************************************************************************
	// Storing Elements By FindBy anotation
	@FindBy(xpath = "//p[text()='Your job has been submitted!']")
	WebElement JobPostedSuccessMessageText;

	// User Defined Method To Above Elements
	public WebElement JobPostedSuccessMessageText() {
		return JobPostedSuccessMessageText;
	}

}
