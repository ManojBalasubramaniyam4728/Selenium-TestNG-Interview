package com.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostAJob_Budget_And_Timeline_Page {
	// WedDriver invoking
	WebDriver driver;

	// Giving life to driver by Constracor
	public PostAJob_Budget_And_Timeline_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// **************************************************************************************************************************************
	// Storing Elements By FindBy anotation
	@FindBy(xpath = "//p[text()='Budget & Timeline']")
	WebElement BudgetAndTimelineText;

	@FindBy(xpath = "//input[@placeholder='Enter budget']")
	WebElement BudgetAmmountTextfield;

	@FindBy(xpath = "//input[@id='currency']")
	WebElement CurrencyTypeDropdown;

	@FindBy(xpath = "//input[@placeholder='On date']")
	WebElement OnDatePicker;

	@FindBy(xpath = "//input[@placeholder='Before date']")
	WebElement BeforeDatePicker;
	
	@FindBy(xpath = "//button/span[text()='Continue']")
	WebElement ContinueButton1;

	// User Defined Method To Above Elements
	public WebElement BudgetAndTimelineText() {
		return BudgetAndTimelineText;
	}

	public WebElement BudgetAmmountTextfield() {
		return BudgetAmmountTextfield;
	}

	public WebElement CurrencyTypeDropdown() {
		return CurrencyTypeDropdown;
	}

	public WebElement OnDatePicker() {
		return OnDatePicker;
	}

	public WebElement BeforeDatePicker() {
		return BeforeDatePicker;
	}
	
	public WebElement ContinueButton1() {
		return ContinueButton1;
	}

	// Dynamic Element
	public WebElement Ondata(String Ondata) {
		By OndataPicker = By.xpath("(//td[@title='"+Ondata+"'])[1]");
		return driver.findElement(OndataPicker);
	}
	
	public WebElement BeforeDate(String Befouredata) {
		By BeforeDatePicker = By.xpath("(//td[@title='"+Befouredata+"'])[2]");
		return driver.findElement(BeforeDatePicker);
	}
}
