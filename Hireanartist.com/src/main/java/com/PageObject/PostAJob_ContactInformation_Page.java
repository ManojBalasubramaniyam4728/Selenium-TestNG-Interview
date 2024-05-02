package com.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostAJob_ContactInformation_Page {
	// WedDriver invoking
	WebDriver driver;

	// Giving life to driver by Constracor
	public PostAJob_ContactInformation_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

// **************************************************************************************************************************************
	// Storing Elements By FindBy anotation
	@FindBy(xpath = "//p[text()='Contact Information']")
	WebElement ContactInformationText;
	
	@FindBy(xpath = "//input[@id='mobile']")
	WebElement PhoneNumberTextfiled;
	
	@FindBy(xpath = "//input[@id='first_name']")
	WebElement FirstNameTextfiled;
	
	@FindBy(xpath = "//input[@id='last_name']")
	WebElement LastNameTextfiled;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement EmailIDTextfiled;

	@FindBy(xpath = "//input[@id='password']")
	WebElement PasswordTextfiled;
	
	@FindBy(xpath = "//input[@id='terms_privacy']")
	WebElement termsAndCondutionsCheckbox;
	
	@FindBy(xpath = "//iframe[@title='reCAPTCHA']")
	WebElement ReCaptchaIframe;
	
	@FindBy(xpath = "//span[@id='recaptcha-anchor']")
	WebElement IamNotARobotCheckbox;
	
	@FindBy(xpath = "//span[text()='Save & submit']")
	WebElement SaveAndSubmitButton;


	// User Defined Method To Above Elements
	public WebElement ContactInformationText() {
		return ContactInformationText;
	}
	
	public WebElement PhoneNumberTextfiled() {
		return PhoneNumberTextfiled;
	}
	
	public WebElement FirstNameTextfiled() {
		return FirstNameTextfiled;
	}
	
	public WebElement LastNameTextfiled() {
		return LastNameTextfiled;
	}
	
	public WebElement EmailIDTextfiled() {
		return EmailIDTextfiled;
	}
	
	public WebElement PasswordTextfiled() {
		return PasswordTextfiled;
	}
	
	public WebElement termsAndCondutionsCheckbox() {
		return termsAndCondutionsCheckbox;
	}
	
	public WebElement ReCaptchaIframe() {
		return ReCaptchaIframe;
	}
	
	public WebElement IamNotARobotCheckbox() {
		return IamNotARobotCheckbox;
	}
	
	public WebElement SaveAndSubmitButton() {
		return SaveAndSubmitButton;
	}


	// Dynamic Element
	public WebElement PersonalInterestOptions(String personalinterest) {
		By PersonalInterestOptions = By.xpath("//span[@class='ant-radio-button']/..//*[text()='"+personalinterest+"']");
		return driver.findElement(PersonalInterestOptions);
	}

}
