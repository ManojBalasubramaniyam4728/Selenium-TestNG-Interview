package com.Utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.PageObject.PostAJob_Budget_And_Timeline_Page;
import com.PageObject.PostAJob_ContactInformation_Page;
import com.PageObject.PostAJob_Design_Page;
import com.PageObject.PostAJob_ProjectDetail_Page;
import com.PageObject.PostAJob_Your_Job_Has_Been_Submitted_Page;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	// global Variables
	public static WebDriver driver;
	public static Logger logger;
	public static String screenshootFilePath = "screenshoot/";
	public static PostAJob_ProjectDetail_Page pajdp;
	public static PostAJob_Design_Page pad;
	public static PostAJob_Budget_And_Timeline_Page pabdtp;
	public static PostAJob_ContactInformation_Page pacip;
	public static PostAJob_Your_Job_Has_Been_Submitted_Page payjsp;
	public static RandomUtils ru;
	public static String excelPath = "Hireanartist_TestCase_And_TestData.xlsx";
	public static String testConfigSheetName = "Test Config";
	public static String TestDataSheetName = "Test Data For The Test Case";
	public static String uniqueDataTestConfig = "Test";
	public static String uniqueDataTC001 = "TC001";
	public static LinkedHashMap<String, String> testConfigKeyValue = new LinkedHashMap<String, String>();
	public static LinkedHashMap<String, String> TC001KeyValue = new LinkedHashMap<String, String>();
	public static boolean isTableVertical = true;
	public static boolean isTableHorizontal = false;

//**************************************************************************************************************************************
	// User Defined Method To Invoking Browsers
	public WebDriver initializeDriver() throws IOException {
		testConfigKeyValue = getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
		String browserName = fetchDatFromMap(testConfigKeyValue, "Browser_Name");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}

		else {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}

		return driver;
	}

//**************************************************************************************************************************************
	// User Defined Method For Taking Screnshot On Failed Steps
	public void getScreenShot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenshootFilePath + result + " screenshot.png"));
	}

//**************************************************************************************************************************************
	// User Defined Method Get Data FromExcel
	public static LinkedHashMap<String, String> getDataFromExcel(String excelPath, String sheetName, String uniqueData,
			boolean isTableVertical) throws IOException {
		FileInputStream fisExcel = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(fisExcel);
		DataFormatter df = new DataFormatter();
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		Sheet sheet = workbook.getSheet(sheetName);
		int lastRowNumber = sheet.getLastRowNum(); // return index ==> index

		String value = "";
		String actualTestCaseName = "";
		String actualKey = "";

		// For Horizontal Data Featching In Key Value Pair
		if (isTableVertical == false) {
			for (int i = 0; i <= lastRowNumber; i++) {
				String actualTestcase = df.formatCellValue(sheet.getRow(i).getCell(0));
				if (actualTestcase.equals(uniqueData)) {
					short lastcellNumber = sheet.getRow(i).getLastCellNum(); // return count/size ==> count-1
					for (int j = 1; j < lastcellNumber - 1; j++) {
						actualKey = df.formatCellValue(sheet.getRow(i - 1).getCell(j));
						value = df.formatCellValue(sheet.getRow(i).getCell(j));
						map.put(actualKey, value);
					}
					break;
				}
			}
		}

		// For Vertical Data Featching In Key Value Pair
		else if (isTableVertical == true) {
			for (int i = 1; i <= sheet.getRow(i).getLastCellNum(); i++) {

				try {
					actualTestCaseName = df.formatCellValue(sheet.getRow(0).getCell(i));

				} catch (Exception e) {
				}
				if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
					for (int j = 0; j <= sheet.getLastRowNum(); j++) {

						try {
							actualKey = df.formatCellValue(sheet.getRow(j).getCell(i - 1));
							try {
								value = df.formatCellValue(sheet.getRow(j).getCell(i));
							} catch (Exception e) {
							}

							if ((actualKey.isEmpty() && value.isEmpty()) || actualKey.isEmpty()) {
							} else {
								map.put(actualKey, value);
							}
						} catch (Exception e) {
						}
					}
					break;
				}
			}
		}
		workbook.close();
		fisExcel.close();
		return map;
	}

//**************************************************************************************************************************************
	// User Defined Method Get Data Map
	public static String fetchDatFromMap(LinkedHashMap<String, String> testConfigKeyValue, String value) {
		return value = testConfigKeyValue.get(value);
	}

//**************************************************************************************************************************************
	// User Defined Method write Data FromExcel
	public static void WriteDataInToExcel(String excelPath, String sheetName, String uniqueData, String header,
		String data, boolean isTableVertical) throws EncryptedDocumentException, IOException {
		FileInputStream excelFile = new FileInputStream(new File(excelPath));
		Workbook workbook = WorkbookFactory.create(excelFile);
		Sheet sheet = workbook.getSheet(sheetName);
		DataFormatter df = new DataFormatter();
		boolean flag = false;
		String actualTestCaseName = "";
		String actualKey = "";

		// For Horizontal Data Write Into Excel
		if (isTableVertical == false) {
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				try {
					actualTestCaseName = df.formatCellValue(sheet.getRow(i).getCell(0));
				} catch (Exception e) {
				}
				if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
					for (int j = 1; j <= sheet.getRow(i).getLastCellNum(); j++) {
						try {
							actualKey = df.formatCellValue(sheet.getRow(i - 1).getCell(j));
						} catch (Exception e) {
						}
						if (actualKey.equalsIgnoreCase(header)) {
							try {
								sheet.getRow(i).createCell(j).setCellValue(data);
							} catch (Exception e) {
							}
							flag = true;
							break;
						}
					}
				}
				if (flag == true) {
					break;
				}
			}
		}
		// For Vertical Data Write Into Excel
		else if (isTableVertical == true) {
			for (int i = 1; i <= sheet.getRow(i).getLastCellNum(); i++) {

				try {
					actualTestCaseName = df.formatCellValue(sheet.getRow(0).getCell(i));

				} catch (Exception e) {
				}
				if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
					for (int j = 0; j <= sheet.getLastRowNum(); j++) {

						try {
							actualKey = df.formatCellValue(sheet.getRow(j).getCell(i - 1));
						} catch (Exception e) {
						}
						if (actualKey.equalsIgnoreCase(header)) {
							try {
								sheet.getRow(j).createCell(i).setCellValue(data);
							} catch (Exception e) {
							}
							flag = true;
							break;
						}
					}
				}
				if (flag == true) {
					break;
				}
			}
		}
		FileOutputStream fos = new FileOutputStream(excelPath);
		workbook.write(fos);
		fos.close();
		workbook.close();
		excelFile.close();
	}

//**************************************************************************************************************************************
	// User Defined Method To Explicit Wait
	public void WaitUntilvisibilityOfElement(WebElement Element) throws IOException {
		testConfigKeyValue = getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
		String explicitWait = fetchDatFromMap(testConfigKeyValue, "Explicit_Wait_Time");
		Integer explicitWaitTime = Integer.parseInt(explicitWait);
		// logic gos here
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		wait.until(ExpectedConditions.visibilityOf(Element));
	}

//**************************************************************************************************************************************
	// User Defined Method To Implicitly Wait
	public void WaitImplicitly() throws IOException {
		testConfigKeyValue = getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
		String implicitlyWait = fetchDatFromMap(testConfigKeyValue, "Implicit_Wait _Time");
		Integer implicitlyWaitTime = Integer.parseInt(implicitlyWait);
		// logic gos here
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
	}

// **************************************************************************************************************************************
	// Verify Element Is having Expected Text
	public boolean verifyElementIsHavingExpectedText(WebElement element, String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getText();
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.equalsIgnoreCase(expectedText);
		} else {
			isTextMatching = actualText.equals(expectedText);
		}
		return isTextMatching;
	}

	// **************************************************************************************************************************************
	// Verify Element Is Contains Expected Text
	public boolean verifyElementIsContainsExpectedText(WebElement element, String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getText();
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.toLowerCase().contains(expectedText);
		} else {
			isTextMatching = actualText.contains(expectedText);
		}

		return isTextMatching;

	}

// **************************************************************************************************************************************
	// Verify Attribute Of Element Is having Expected Text
	public boolean verifyAttributOfElementIsHavingExpectedText(WebElement element, String attributName,
			String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getAttribute(attributName);
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.equalsIgnoreCase(expectedText);
		} else {
			isTextMatching = actualText.equals(expectedText);
		}

		return isTextMatching;

	}

// **************************************************************************************************************************************
	// Verify Attribute Of Element Is Contains Expected Text
	public boolean verifyAttributOfElementIsContainsExpectedText(WebElement element, String attributName,
			String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getAttribute(attributName);
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.toLowerCase().contains(expectedText);
		} else {
			isTextMatching = actualText.contains(expectedText);
		}

		return isTextMatching;

	}

// **************************************************************************************************************************************
	// Verify Element is displayed are Not
	public boolean verifyElementIsDisplayedAreNot(WebElement element) {
		boolean isDisplayed;
		try {
			isDisplayed = element.isDisplayed();
			isDisplayed = true;

		} catch (NoSuchElementException e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

// **************************************************************************************************************************************
	// Enter Input Into The Element
	public void enterInputIntoTheElement(WebElement element, String input) {
		element.sendKeys(input);
	}

// **************************************************************************************************************************************
	// Clear The Text And Enter The Input Into The Element
	public void clearTheTextAndEnterInputIntoTheElement(WebElement element, String input) {
		element.clear();
		element.sendKeys(input);
	}

// **************************************************************************************************************************************

	// Convert ArrayList To String
	public String arrayListToString(ArrayList<String> arrayList) {
		// Convert ArrayList to String using toString() method
		String arrayListAsString = arrayList.toString();
		return arrayListAsString;
	}

// **************************************************************************************************************************************
	// Convert String to Boolean
	public boolean stringToBoolean(String Value) {
		boolean booleanValue = Boolean.parseBoolean(Value);
		return booleanValue;
	}
	
// **************************************************************************************************************************************
   // Click On The Element
	public void clickOnTheElement(WebElement element) {
	 element.click();
	}
	
// **************************************************************************************************************************************
	 // robot arrow down
	public void pressArrowDownKey() throws AWTException, InterruptedException {
	Robot robot=new Robot();
	robot.keyPress(KeyEvent.VK_DOWN);
	Thread.sleep(700);
    robot.keyRelease(KeyEvent.VK_DOWN);
		}
		
// **************************************************************************************************************************************
	 // robot arrow down
	public void PressEnterKey() throws AWTException, InterruptedException {
	Robot robot=new Robot();
	robot.keyPress(KeyEvent.VK_ENTER);
	Thread.sleep(700);
	robot.keyRelease(KeyEvent.VK_ENTER);
  } 
	
// **************************************************************************************************************************************
		 // robot arrow down
		public void PressBackspaceNey() throws AWTException, InterruptedException {
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(700);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
	  }
	
//**************************************************************************************************************************************
    //User Defined Method For Scroll By JavascriptExecutor
    public static void scrollTillElementIsvisable(WebElement element) {
		JavascriptExecutor scroll = (JavascriptExecutor) driver;
		scroll.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
//**************************************************************************************************************************************
   // Move to the element Perform click on current location
    public void moveToElementAndClick(WebElement element) {
    Actions actions=new Actions(driver);
        actions.moveToElement(element);
        actions.click().perform();
    }
    
//**************************************************************************************************************************************
    // Upload A File
     public void uploadeAFile(WebElement element, String filePath) {
         element.sendKeys(filePath);
     }
     
//**************************************************************************************************************************************
     // copy to clipboard
      public void copyToClipboard(String filePath) throws AWTException, InterruptedException {

          // Copy file path to clipboard
          System.setProperty("java.awt.headless", "false");
     	 StringSelection str = new StringSelection(filePath);
  	      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
      }
      
//**************************************************************************************************************************************
      // Upload A File using robot class
      public void uploadFileUsingRobot(String filePath) throws AWTException, InterruptedException {
      Robot robot=new Robot();
    	  
      // Copy file path to clipboard
      StringSelection selection = new StringSelection(filePath);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);


      // Paste file path from clipboard into file dialog
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      Thread.sleep(700);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      Thread.sleep(700);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);

      }
 		
//**************************************************************************************************************************************
      // Switch to the iframe
      public static void switchToFrame(WebElement iframeElement) {
          driver.switchTo().frame(iframeElement);
      }
      
//**************************************************************************************************************************************
      // Switch to the Parent Frame iframe
      public static void switchToparentFrame() {
          driver.switchTo().parentFrame();
      }
      
//**************************************************************************************************************************************
    //User Defined Method For Closing The Browser
  	public void closingDriver() {
  	       driver.close();
  	       driver=null;
  	} 
}
