package com.org.frameworkFiles.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.org.frameworkFiles.actionKeywords.UIActions;
/***************************************************
 * Class Name: GetInTouchPageObjects
 * Class Description: To initialize all page objects or element locator objects and everything relating to Get In Touch page 
 * Date Created: 28/01/2023
 * @author : Jerry Nkomo
 */
public class GetInTouchPageObjects extends UIActions {
	@FindBy(how = How.XPATH, using="//button[@data-cky-tag='accept-button']")
	@CacheLookup
	public static WebElement OBJ_AcceptAll_Button;
	
	@FindBy(how = How.XPATH, using="//a[contains(text(),'Get in Touch')]")
	@CacheLookup
	public static WebElement OBJ_GetInTouch_Link;
	
	/***************************************************
	 * Method Name: objects
	 * Method Description: To initialize all page objects or element locator objects within the page
	 * Date Created: 28/01/2023
	 * Author: Jerry Nkomo
	 * @param driver
	 */
	public static void objects(WebDriver driver) {
		UIActions.driver = driver;
		PageFactory.initElements(driver, GetInTouchPageObjects.class);
	}
	
	/***************************************************
	 * Method Name: launchURL
	 * Method Description: To launch application URL
	 * Date Created: 28/01/2023
	 * Author: Jerry Nkomo
	 * @param strURL
	 */
	public static void launchURL(String strURL) {
		driver.get(strURL);
		String strTitle = driver.getTitle();
		
		try {
			if(strTitle.contains("Software Quality Assurance")) {
				logger.log(Status.PASS, strURL+" Launched Successfully");
				captureScreenShot("Launch URL");
			}else {
				logger.log(Status.FAIL, "Unable to Launch " + strURL);
				captureScreenShot("Launch URL");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
