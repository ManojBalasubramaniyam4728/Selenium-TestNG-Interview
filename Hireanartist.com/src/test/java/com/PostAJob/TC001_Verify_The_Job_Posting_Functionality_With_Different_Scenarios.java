package com.PostAJob;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.PageObject.PostAJob_Budget_And_Timeline_Page;
import com.PageObject.PostAJob_ContactInformation_Page;
import com.PageObject.PostAJob_Design_Page;
import com.PageObject.PostAJob_ProjectDetail_Page;
import com.PageObject.PostAJob_Your_Job_Has_Been_Submitted_Page;
import com.Utils.RandomUtils;
import com.Utils.TestBase;

public class TC001_Verify_The_Job_Posting_Functionality_With_Different_Scenarios extends TestBase {
	
	@BeforeTest
	public void open_Browser_And_Navigate_To_Vtiger_Url_And_Login_To_Application() throws IOException {
		// Reading The logger
		logger = Logger.getLogger("Post A Job");// Adding logger
		PropertyConfigurator.configure("log4j.properties");// Adding logger
	
		testConfigKeyValue=getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
        String url=fetchDatFromMap(testConfigKeyValue, "Web_URL");
		driver = initializeDriver();
		logger.info("Browser Opened Successfully"); 
		driver.get(url);
		logger.info("Successfully Navigated to Hireanartist url ");
		WaitImplicitly();
	}
	
	@Test
	public void Post_A_Job() throws IOException, AWTException, InterruptedException {
		
		//Given life of driver to Page Object
		pajdp=new PostAJob_ProjectDetail_Page(driver);
		pad=new PostAJob_Design_Page(driver);
		pabdtp=new PostAJob_Budget_And_Timeline_Page(driver);
		pacip=new PostAJob_ContactInformation_Page(driver);
		ru=new RandomUtils();
		payjsp=new PostAJob_Your_Job_Has_Been_Submitted_Page(driver);
		
		//settings to obtaining Data from Excel
		TC001KeyValue=getDataFromExcel(excelPath,TestDataSheetName, uniqueDataTC001, isTableHorizontal);
		testConfigKeyValue=getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		
		//Accept The cookies if is present
		boolean cookiesIsPresentOrNot = verifyElementIsDisplayedAreNot(pajdp.accepetCookiesButton());
		if (cookiesIsPresentOrNot) {
			clickOnTheElement(pajdp.accepetCookiesButton());
			logger.info("Successfully Clicked On the accepet Cookies Button");
		}
		else {
			logger.info("cookies Is Not Present In The Page");
		}
		
		//obtaining Data from Excel
		String postAJobVerification = fetchDatFromMap(TC001KeyValue, "Post_A_Job_Verification");
		//verifing the element
		verifyElementIsHavingExpectedText(pajdp.PostAJobText(), postAJobVerification, ignoreCase);
		//logging to Log file
		logger.info("Post A Job Element Is Having Expected "+postAJobVerification+" Text");
		
		//obtaining Data from Excel
		String projectDetailsPageVerification = fetchDatFromMap(TC001KeyValue, "Project_Details_Page_Verification");
		//verifing the element
		verifyElementIsHavingExpectedText(pajdp.LetsStartWiththeBasicsText(), projectDetailsPageVerification, ignoreCase);
		//logging to Log file
		logger.info("Let's Start With the Basics Element Is Having Expected "+projectDetailsPageVerification+" Text");
		
		//performing click  Action
		clickOnTheElement(pajdp.GiveYourProjectANameTextField());
		//logging to Log file
		logger.info("Successfully Clicked On the Give Your Project A Name TextField");
		
		//obtaining Data from Excel
		String giveYourProjectAName = fetchDatFromMap(TC001KeyValue, "Give_Your_Project_A_Name");
		//performing Entering  Action
		enterInputIntoTheElement(pajdp.GiveYourProjectANameTextField(), giveYourProjectAName);
		//logging to Log file
		logger.info("Successfully Entered "+giveYourProjectAName +"Into give Your Project A Name Textfield");
		
		//obtaining Data from Excel
		String whatKindOfArtAreYouRequesting = fetchDatFromMap(TC001KeyValue, "What_Kind_Of_Art_Are_You_Requesting");
		//performing click  Action Dynamicaly
		clickOnTheElement(pajdp.WhatKindOfArtAreYouRequestingOptions(whatKindOfArtAreYouRequesting));
		//logging to Log file
		logger.info("Successfully Clicked On the "+whatKindOfArtAreYouRequesting+" Options");
		
		//obtaining Data from Excel
		String whereWillTheMuralGo = fetchDatFromMap(TC001KeyValue, "Where_Will_The_Mural_Go");
		//performing click  Action Dynamicaly
		clickOnTheElement(pajdp.WhereWillTheMuralGoCheckBox(whereWillTheMuralGo));
		//logging to Log file
		logger.info("Successfully Clicked On the "+whereWillTheMuralGo+" CheckBox");
		
		//performing click 
		clickOnTheElement(pajdp.WidthTextfield());
		//logging to Log file
		logger.info("Successfully Clicked On the Width Textfield");
		
		//obtaining Data from Excel
		String width = fetchDatFromMap(TC001KeyValue, "Width");
		//performing Entering  Action
		enterInputIntoTheElement(pajdp.WidthTextfield(),width );
		//logging to Log file
		logger.info("Successfully Entered "+width +"Into width Textfield");
		
		//performing click 
		clickOnTheElement(pajdp.HeightTextfield());
		//logging to Log file
		logger.info("Successfully Clicked On the height Textfield");
				
		//obtaining Data from Excel
		String height = fetchDatFromMap(TC001KeyValue, "Hight");
		//performing Entering  Action
		enterInputIntoTheElement(pajdp.HeightTextfield(),height );
		//logging to Log file
		logger.info("Successfully Entered "+height +"Into Hight Textfield");
		
		//obtaining Data from Excel
		String measureBy = fetchDatFromMap(TC001KeyValue, "Measure_By");
	    //performing click  Action Dynamicaly
		clickOnTheElement(pajdp.MeasureByOption(measureBy));
		//logging to Log file
		logger.info("Successfully Clicked On the "+measureBy+" Option");
		
		//Perform scroll action
		scrollTillElementIsvisable(pajdp.LocationOfTheProject());
		//logging to Log file
		logger.info("Successfully scrolled till Location Of The Project Textfield");
		
		//performing click 
	    clickOnTheElement(pajdp.LocationOfTheProject());
		//logging to Log file
		logger.info("Successfully Clicked On the Location Of The Project Textfield");
		
		//obtaining Data from Excel
	    String locationOfTheProject = fetchDatFromMap(TC001KeyValue, "Location_Of_The_Project");
		//performing Entering  Action
		enterInputIntoTheElement(pajdp.LocationOfTheProject(),locationOfTheProject );
		//logging to Log file
		logger.info("Successfully Entered "+locationOfTheProject +"Into location Of The Project Textfield");
		
		//performing click 
	    clickOnTheElement(pajdp.LocationOfTheProject());
		//logging to Log file
		logger.info("Successfully Clicked On the Location Of The Project Textfield");
		
		//since selecting option is in shadow dom so going for robot class
		PressBackspaceNey();
		pressArrowDownKey();
		PressEnterKey();
		//logging to Log file
		logger.info("Successfully Selected location Of The Project");
		
		//obtaining Data from Excel
		String typeOfProperty = fetchDatFromMap(TC001KeyValue, "Type_Of_Property");
		//Move to the element Perform click on current location by action class
	    moveToElementAndClick(pajdp.TypeOfPropertyOption(typeOfProperty));
		//logging to Log file
		logger.info("Successfully Clicked On the "+typeOfProperty+" Option");
		
		//performing click 
		clickOnTheElement(pajdp.ContinueButton());
		//logging to Log file
		logger.info("Successfully Clicked On the Continue Button");
		
		//obtaining Data from Excel
		String designPageVerification = fetchDatFromMap(TC001KeyValue, "Design_Page_Verification");
		//verifing the element
		verifyElementIsHavingExpectedText(pad.DesignText(), designPageVerification, ignoreCase);
		//logging to Log file
		logger.info("Design Text Element Is Having Expected "+designPageVerification+" Text");
		
		//obtaining Data from Excel
		String whereInTheDesignProcessAreYou = fetchDatFromMap(TC001KeyValue, "Where_In_The_Design_Process_Are_You");
	    //performing click  Action Dynamicaly
	    clickOnTheElement(pad.WhereInTheDesignProcessAreYouradioButton(whereInTheDesignProcessAreYou));
		//logging to Log file
		logger.info("Successfully Clicked On the "+whereInTheDesignProcessAreYou+" radio button Option");
		
		//performing click 
		clickOnTheElement(pad.JobDescriptionTextarea());
		//logging to Log file
		logger.info("Successfully Clicked On the Job Description Textarea");
		
		//obtaining Data from Excel
		String jobDescription = fetchDatFromMap(TC001KeyValue, "Job_Description");
		//performing Entering  Action
		enterInputIntoTheElement(pad.JobDescriptionTextarea(), jobDescription);
		//logging to Log file
		logger.info("Successfully Entered "+jobDescription +"Into job Description Textarea");
		
		//Perform scroll action
		scrollTillElementIsvisable(pad.AttachfileButton());
		//logging to Log file
		logger.info("Successfully scrolled till Attach file Button");
		
		//performing click 
	    clickOnTheElement(pad.AttachfileButton());
		//logging to Log file
	    logger.info("Successfully Clicked On the Attachfile Button");
		
	    Thread.sleep(2000);
	    //obtaining Data from Excel
	    String dummyFilepath = fetchDatFromMap(TC001KeyValue, "Dummy_Filepath");
		//Upload the dummyn file using robot class because it is in span tag
	    uploadFileUsingRobot(dummyFilepath);
	    Thread.sleep(1000);
		//logging to Log file
		logger.info("Successfully Uploaded "+dummyFilepath+"To Attach file button");
		
		//performing click 
	    clickOnTheElement(pad.ContinueButton());
		//logging to Log file
	    logger.info("Successfully Clicked On the Continue Button");
	     
	    //obtaining Data from Excel
	    String BudgetAndTimelineVerification = fetchDatFromMap(TC001KeyValue, "Budget_&_Timeline_Verification");
	    //verifing the element
	    verifyElementIsHavingExpectedText(pabdtp.BudgetAndTimelineText(), BudgetAndTimelineVerification, ignoreCase);
	    //logging to Log file
	    logger.info("Budget And Timeline Textt Element Is Having Expected "+BudgetAndTimelineVerification+" Text");
	  		
		//performing click 
	    clickOnTheElement(pabdtp.BudgetAmmountTextfield());
		//logging to Log file
	    logger.info("Successfully Clicked On Budget Ammount Textfield");
	    
	    //obtaining Data from Excel
	  	String budgetAmmount = fetchDatFromMap(TC001KeyValue, "Budget_Ammount");
	  	//performing Entering  Action
	  	enterInputIntoTheElement(pabdtp.BudgetAmmountTextfield(), budgetAmmount);
	  	//logging to Log file
	  	logger.info("Successfully Entered "+budgetAmmount +"Into budget Ammount textfield");
	  	
	    //Move to the element Perform click on current location by action class
	    moveToElementAndClick(pabdtp.CurrencyTypeDropdown());
		//logging to Log file
	    logger.info("Successfully Clicked On Currency Type Dropdown");
	    
	    //obtaining Data from Excel
	  	String currencyType = fetchDatFromMap(TC001KeyValue, "Currency_Type");
	  	//performing Entering  Action
	  	enterInputIntoTheElement(pabdtp.CurrencyTypeDropdown(), currencyType);
	  	//logging to Log file
	  	logger.info("Successfully Entered "+currencyType +"Into Currency Type Dropdown");
	  	//Press Enter key sinces it is not in select tage
	  	PressEnterKey();
		
		//Perform scroll action
		scrollTillElementIsvisable(pabdtp.OnDatePicker());
		//logging to Log file
		logger.info("Successfully scrolled till On data date picker");
		
		//performing click 
	    clickOnTheElement(pabdtp.OnDatePicker());
		//logging to Log file
	    logger.info("Successfully Clicked On On Date Picker");
	    
	    //obtaining Data from Excel
	  	String OnDate = fetchDatFromMap(TC001KeyValue, "On_Date");
	  	//select on date which is given bt the user
	  	clickOnTheElement(pabdtp.Ondata(OnDate));
	    //logging to Log file
	    logger.info("Successfully Clicked On "+OnDate);
	    
	   
	    //This is commentd Based on condution given if user want befour date than on date should be commenteds
	    
//	    //performing click 
//	    clickOnTheElement(pabdtp.BeforeDatePicker());
//		//logging to Log file
//	    logger.info("Successfully Clicked On Before Date Picker");
//	    
//	    //obtaining Data from Excel
//	  	String beforeData = fetchDatFromMap(TC001KeyValue, "Before_Data");
//	  	//select on date which is given bt the user
//	  	clickOnTheElement(pabdtp.BeforeDate(beforeData));
//	    //logging to Log file
//	    logger.info("Successfully Clicked On "+beforeData);
	    
	    //performing click 
	    clickOnTheElement(pajdp.ContinueButton());
	    //logging to Log file
	    logger.info("Successfully Clicked On the Continue Button");
	    
	    //obtaining Data from Excel
	    String contactInformationVerification = fetchDatFromMap(TC001KeyValue, "Contact_Information_Verification");
	    //verifing the element
	    verifyElementIsHavingExpectedText(pacip.ContactInformationText(), contactInformationVerification, ignoreCase);
	    //logging to Log file
	    logger.info("contact Information Textt Element Is Having Expected "+contactInformationVerification+" Text");
	  		
	    //obtaining Data from Excel
	    String personalInterestOptions = fetchDatFromMap(TC001KeyValue, "Personal_Interest_Options");
	    //performing click  Action Dynamicaly
	    clickOnTheElement(pacip.PersonalInterestOptions(personalInterestOptions));
	    //logging to Log file
	  	logger.info("Successfully Clicked On the "+personalInterestOptions+" Option");
	    
	    //performing click 
	    clickOnTheElement(pacip.PhoneNumberTextfiled());
	    //logging to Log file
	    logger.info("Successfully Clicked On Phone Number Textfiled");
	   
	    //obtaining random Utils
	  	String phoneNumber=RandomUtils.getPhoneNumber();
	  	//performing Entering  Action
	  	enterInputIntoTheElement(pacip.PhoneNumberTextfiled(), phoneNumber);
	  	//logging to Log file
	  	logger.info("Successfully Entered "+phoneNumber +" Into Phone Number Textfiled");
	  	//writting back to excel
	    WriteDataInToExcel(excelPath, TestDataSheetName, "TC001", "Phone_Number_Random_Generated", phoneNumber,isTableHorizontal );
	  	
	  	//performing click 
	    clickOnTheElement(pacip.FirstNameTextfiled());
	    //logging to Log file
	    logger.info("Successfully Clicked On First Namer Textfiled");
	    
	    //obtaining random Utils
	  	String FirstName=RandomUtils.getFirstName();
	  	//performing Entering  Action
	  	enterInputIntoTheElement(pacip.FirstNameTextfiled(), FirstName);
	  	//logging to Log file
	  	logger.info("Successfully Entered "+FirstName +" Into First Name Textfiled");
	    //writting back to excel
	    WriteDataInToExcel(excelPath, TestDataSheetName, "TC001", "First_Name_Random_Generated", FirstName,isTableHorizontal );
	    
	  	
	    //performing click 
	    clickOnTheElement(pacip.LastNameTextfiled());
	    //logging to Log file
	    logger.info("Successfully Clicked On Last Namer Textfiled");
	    
	    //obtaining random Utils
	  	String LastName=RandomUtils.getLastName();
	  	//performing Entering  Action
	  	enterInputIntoTheElement(pacip.LastNameTextfiled(), LastName);
	  	//logging to Log file
	  	logger.info("Successfully Entered "+LastName +" Into Last Name Textfiled");
	  	//writting back to excel
	    WriteDataInToExcel(excelPath, TestDataSheetName, "TC001", "Last_Name_Random_Generated", LastName,isTableHorizontal );
	  	
	  	//performing click 
	    clickOnTheElement(pacip.EmailIDTextfiled());
	    //logging to Log file
	    logger.info("Successfully Clicked On Email ID Textfiled");
	    
	    //obtaining random Utils
	  	String emailID=RandomUtils.getEmail();
	  	//performing Entering  Action
	  	enterInputIntoTheElement(pacip.EmailIDTextfiled(), emailID);
	  	//logging to Log file
	  	logger.info("Successfully Entered "+emailID +" Into Email ID Textfiled");
	  	//writting back to excel
	    WriteDataInToExcel(excelPath, TestDataSheetName, "TC001", "Email_Id_Random_Generated", emailID,isTableHorizontal );
	  	
	    //performing click 
	    clickOnTheElement(pacip.PasswordTextfiled());
	    //logging to Log file
	    logger.info("Successfully Clicked On Password Textfiled");
	    
	    //obtaining random Utils
	  	String password=RandomUtils.getPassword();
	  	//performing Entering  Action
	  	enterInputIntoTheElement(pacip.PasswordTextfiled(), password);
	  	//logging to Log file
	  	logger.info("Successfully Entered "+password +" Password Textfiled");
	  	//writting back to excel
	    WriteDataInToExcel(excelPath, TestDataSheetName, "TC001", "Create_Password_Random_Generated", password,isTableHorizontal );
	    
	    //Perform scroll action
	  	scrollTillElementIsvisable(pacip.SaveAndSubmitButton());
	  	//logging to Log file
	  	logger.info("Successfully scrolled till Save And Submit Button");
	  	
	    //performing click 
	    clickOnTheElement(pacip.termsAndCondutionsCheckbox());
	    //logging to Log file
	    logger.info("Successfully Clicked On terms And Condutions Checkbox");
	    
	    //Switch to Iframe
	    switchToFrame(pacip.ReCaptchaIframe());
	    logger.info("Successfully Switched To Captcha Iframe");
	    
	    //performing click 
	    clickOnTheElement(pacip.IamNotARobotCheckbox());
	    //logging to Log file
	    logger.info("Successfully Clicked On Iam Not A RobotCheckbox");
	    
	    //This wait is to handel captch manualy
	    Thread.sleep(15000);
	    
	    //Switch To Parent fram
	    switchToparentFrame();
	    logger.info("Successfully Backed To parent Iframe");
	    
	    //performing click 
	    clickOnTheElement(pacip.SaveAndSubmitButton());
	    //logging to Log file
	    logger.info("Successfully Clicked On Save And Submit Button");
	    
	    //obtaining Data from Excel
	    String jobPostedSuccessMessage = fetchDatFromMap(TC001KeyValue, "Job_Posted_Success_Message");
	    //verifing the element
	    verifyElementIsHavingExpectedText(pacip.ContactInformationText(), jobPostedSuccessMessage, ignoreCase);
	    //logging to Log file
	    logger.info("Job Posted Success Message Textt Element Is Having Expected "+jobPostedSuccessMessage+" Text");
	}
	
	@AfterClass
	public void close_browser() {
		closingDriver();
		logger.info("Successfully Closed The Browser");
	}
}
