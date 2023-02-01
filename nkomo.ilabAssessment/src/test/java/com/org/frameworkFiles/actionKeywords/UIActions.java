package com.org.frameworkFiles.actionKeywords;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.org.frameworkFiles.utility.GlobalVariables;
/*********************************
 * Class Name: UIActions
 * Class Description: To store all user interface actions
 *  Date Created: 28/01/2023
 *  Last Modified:01/02/2023
 * @author Jerry Nkomo
 *
 */
public class UIActions {
	public static WebDriver driver;
	public static ExtentReports extentReports = new ExtentReports();
	
	public static ExtentSparkReporter sparkReporter;
	
	public static WebDriverWait wait;
	public static ExtentTest features;
	public static ExtentTest logger;
	
	public static WebDriver browser(String browser) throws Exception{
		boolean browserFound = true;
		
		if (browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--enable-extensions");
			options.setExperimentalOption("useAutomationExtension", false);
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} else if (browser.equalsIgnoreCase("FireFox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--enable-extensions");
			options.setCapability("useAutomationExtension", false);
driver=new FirefoxDriver(options);
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} else {
			browserFound = false;
		}
		
		if(browserFound) {
			driver.manage().window().maximize();
			//logger.log(Status.PASS, browser+" browser launched and maximazed successfully");
			return driver;
		}else {
			logger.log(Status.FAIL, "Could not launch "+browser+" browser");
			return null;
		}
	}
	
	public static void closeBrowser() throws Exception{
		try {
			if (driver != null) {
				extentReports.flush();
				driver.quit();
			}
		}catch (Exception e) {
			logger.log(Status.FAIL, "Unable to close browser");
			captureScreenShot("Close Browser");
		}
	}
	
	public static void createReport() throws IOException{
		final File CONF = new File("src/test/resources/extent-config.xml");
		sparkReporter = new ExtentSparkReporter("target/JerryAutomationStepReport.html");
		sparkReporter.loadXMLConfig(CONF);
		extentReports.attachReporter(sparkReporter);
	}
	
	public static void createFeature(String featureName) {
		features = extentReports.createTest(featureName);
	}
	
	public static void scenario(String stepName) {
		logger = features.createNode(stepName);
	}
	
	public static void report() throws InterruptedException {
		extentReports.flush();
	}
	
	public static String getFeatureFileName(Scenario scenario) {
		String featureName="Feature ";
		String ss = scenario.getName();
		
		return featureName;
	}

	public static String captureScreenShot(String fieldName) throws Exception {
		try {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			DateFormat date2 = new SimpleDateFormat("yyyy_MM_dd");
			String strImage = GlobalVariables.OBJ_SCREENSHOT_PATH+"/"+date2.format(date)+"/"+fieldName+format.format(date)+".png";
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(strImage));
			
			return strImage;
		} catch (Exception e) {
			return "error";
		}
	}
	
	public static Recordset readDataFromExcel(String sheetName, String scenario) throws Exception {
		Recordset objRS=null;
		try {
			Fillo objFillo = new Fillo();
			Connection objCon = objFillo.getConnection(GlobalVariables.OBJ_TESTDATASHEET);
			String strQuery = "SELECT * FROM "+sheetName+" WHERE Exec_Flag='Yes' AND Scenario_Name='"+scenario+"'";
			System.out.println(strQuery);
			objRS = objCon.executeQuery(strQuery);
			objRS.next();
			System.out.println(objRS);
			return objRS;
		} catch (Exception e) {
			System.out.println("---------------Error Occured---------------");
			System.out.println(e.getMessage());
			closeBrowser();
		}
		return objRS;
	}
	
	public static String getScenarioName(Scenario scenario) {
		return scenario.getName();
	}
	
	public static String getFeatureName(Scenario scenario) {
		return scenario.getName();
	}
	
	public static Map<String, Map<String, String>> getExcelDataToHashMap(String path, String sheetName){
		Map<String, Map<String, String>> excelData = null;
		try {
			ExcelToHashMap ex = new ExcelToHashMap(path, sheetName);
			excelData = ex.getExcelAsMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelData;
	}
	
	public static boolean elementExists(WebElement element) throws Exception {
		boolean elementExits = false;
		if (element.isDisplayed()) {
			elementExits = true;
		}
		
		return elementExits;
	}
	
	public static void click(String UIName, WebElement elementLocator) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, GlobalVariables.OBJ_MAX_WAIT);
		wait.until(ExpectedConditions.visibilityOf(elementLocator));
		if (elementExists(elementLocator)) {
			wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
			captureScreenShot(UIName);
			elementLocator.click();
			logger.log(Status.PASS,"Clicked "+UIName+" successfully");
			captureScreenShot(UIName);
			
		} else {
			logger.log(Status.FAIL, "Unable to click "+UIName);
			captureScreenShot(UIName);
		}
	}
	
	public static void scrollBy(int firstCoordinate, int secondCoordinate) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy("+firstCoordinate+","+secondCoordinate+")");
	}
	public static void input(String UIName, WebElement elementLocator, String data) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, GlobalVariables.OBJ_MAX_WAIT);
		
		if (elementExists(elementLocator)) {
			wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
			elementLocator.clear();
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", elementLocator);
			
			elementLocator.sendKeys(data);
			logger.log(Status.PASS, "Entered data successfully for "+UIName);
			captureScreenShot(UIName);
			
		} else {
			logger.log(Status.FAIL, "Unable to enter data for "+UIName);
			captureScreenShot(UIName);
		}
	}
	
	public static void switchFrame(String frameName) {
		try {
			//driver.switchTo().defaultContent();
			driver.switchTo().frame(frameName);
			
		}catch (Exception e) {
			logger.log(Status.INFO, "No Frame found");
		}
	}
	
	public static void switchFrame(WebElement frameName) {
		try {
			//driver.switchTo().defaultContent();
			driver.switchTo().frame(frameName);
			
		}catch (Exception e) {
			logger.log(Status.INFO, "No Frame found");
		}
	}
}
