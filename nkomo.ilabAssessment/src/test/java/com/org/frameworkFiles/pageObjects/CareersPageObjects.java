package com.org.frameworkFiles.pageObjects;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.org.frameworkFiles.actionKeywords.UIActions;
/*********************************
 * Class Name: CareersPageObjects
 * Class Description: To initialize all page objects or element locator objects and everything relating to Careers Page
 *  Date Created: 29/01/2023
 * @author Jerry Nkomo
 *
 */
public class CareersPageObjects {
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Careers')]")
	@CacheLookup
	public static WebElement OBJ_Careers_Button;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'South Africa')]")
	@CacheLookup
	public static WebElement OBJ_SouthAfrica_Button;
	
	@FindBy(how = How.XPATH, using="//ul/li/a[contains(text(), 'Software Quality Assurance (SQA) Lead')]")
	@CacheLookup
	public static WebElement OBJ_AvailableJob_Link;
	
	@FindBy(how = How.XPATH, using="//iframe[@id='hs-form-iframe-0']")
	@CacheLookup
	public static WebElement OBJ_Form_IFrame;
	
	@FindBy(how = How.XPATH, using="//input[@id='firstname-91269253-d0ea-4409-a821-873cda679554']")
	@CacheLookup
	public static WebElement OBJ_FirstName_Textbox;
	
	@FindBy(how = How.XPATH, using="//input[@name='lastname']")
	@CacheLookup
	public static WebElement OBJ_Surname_Textbox;
	
	@FindBy(how = How.XPATH, using="//input[@id='email-91269253-d0ea-4409-a821-873cda679554']")
	@CacheLookup
	public static WebElement OBJ_Email_Textbox;
	
	@FindBy(how = How.XPATH, using="//input[@id='phone-91269253-d0ea-4409-a821-873cda679554']")
	@CacheLookup
	public static WebElement OBJ_Phone_Textbox;
	
	@FindBy(how = How.XPATH, using="//input[@type='submit']")
	@CacheLookup
	public static WebElement OBJ_Submit_Button;
	
	@FindBy(how = How.XPATH, using="//label[@class='hs-error-msg']")
	@CacheLookup
	public static WebElement OBJ_Message;
	
	/***************************************************
	 * Method Name: objects
	 * Method Description: To initialize all page objects or element locator objects within the page
	 * Date Created: 29/01/2023
	 * Author: Jerry Nkomo
	 * @param driver
	 */
	public static void objects(WebDriver driver) {
		UIActions.driver = driver;
		PageFactory.initElements(driver, CareersPageObjects.class);
	}
	
	/***************************************
	 * Method Name: generatePhoneNumber
	 * Method Description: To Generate random phone number
	 * Date Created: 29/01/2023
	 * @return phoneNumber
	 */
	public static String generatePhoneNumber() {
		Random rn = new Random();
		String[] answer = new String[9];
		String phoneNumber = "0";
		for(int i =0; i < answer.length; i++)
		{
			int value = rn.nextInt(10);
		    answer[i] = String.valueOf(value);
		    
		}
		
		for (int j = 0; j < answer.length; j++) {
			phoneNumber += answer[j];
		}
		System.out.println(phoneNumber);
		return phoneNumber;
	}
}
