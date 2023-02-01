package com.org.frameworkFiles.stepDefinitions;



import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.org.frameworkFiles.actionKeywords.UIActions;
import com.org.frameworkFiles.pageObjects.CareersPageObjects;
import com.org.frameworkFiles.utility.GlobalVariables;

import io.cucumber.java.en.Then;
/*********************************
 * Class Name: UIActions
 * Class Description: To define steps relating to Careers Page
 *  Date Created: 29/01/2023
 *  Last Modified:01/02/2023
 * @author Jerry Nkomo
 *
 */
public class StepDefinition_Careers extends UIActions {
	@Then("User Proceeds to Careers")
	public void proceedToCareers() throws Throwable {
		CareersPageObjects.objects(driver);
		click("Careers Button", CareersPageObjects.OBJ_Careers_Button);
		scrollBy(0, 700);
		click("South Africa", CareersPageObjects.OBJ_SouthAfrica_Button);
		scrollBy(0, 500);
		click("Software Quality Assurance (SQA) Lead", CareersPageObjects.OBJ_AvailableJob_Link);
		scrollBy(0, 1100);
		
	}
	
	@Then("User Inputs Excel Data")
	public void inputExcelData() throws Exception {
		CareersPageObjects.objects(driver);
		switchFrame(CareersPageObjects.OBJ_Form_IFrame);
		Pattern pattern = Pattern.compile("^(0\\d{2})[ ]?\\d{3}[ ]?\\d{4}$");
		String phone = CareersPageObjects.generatePhoneNumber();
		Matcher patternMatcher = pattern.matcher(phone);
		String firstName = GlobalVariables.objRS.getField("First_Name");
		String email = GlobalVariables.objRS.getField("Email").trim();
		input("First Name", CareersPageObjects.OBJ_FirstName_Textbox, firstName);
		input("Email", CareersPageObjects.OBJ_Email_Textbox, email);
		if(patternMatcher.matches()) {
		input("Phone", CareersPageObjects.OBJ_Phone_Textbox, phone);
		}
		click("Submit", CareersPageObjects.OBJ_Submit_Button);
		
		String message = CareersPageObjects.OBJ_Message.getText();
		
		if(message.contains("Please complete this required field")) {
			logger.log(Status.PASS, "Error message is visible");
			captureScreenShot("Message");
		}else {
			logger.log(Status.PASS, "Error message is not visible");
			captureScreenShot("Message");
		}
	}
	
	@Then("User Inputs {string} And {string}")
	public void user_inputs_and(String Name, String Email) throws Exception {
		CareersPageObjects.objects(driver);
		switchFrame(CareersPageObjects.OBJ_Form_IFrame);
		Pattern pattern = Pattern.compile("^(0\\d{2})[ ]?\\d{3}[ ]?\\d{4}$");
		String phone = CareersPageObjects.generatePhoneNumber();
		Matcher patternMatcher = pattern.matcher(phone);
		input("First Name", CareersPageObjects.OBJ_FirstName_Textbox, Name);
		input("Email", CareersPageObjects.OBJ_Email_Textbox, Email);
		if(patternMatcher.matches()) {
		input("Phone", CareersPageObjects.OBJ_Phone_Textbox, phone);
		}
		click("Submit", CareersPageObjects.OBJ_Submit_Button);
		
		String message = CareersPageObjects.OBJ_Message.getText();
		
		if(message.contains("Please complete this required field")) {
			logger.log(Status.PASS, "Error message is visible");
			captureScreenShot("Message");
		}else {
			logger.log(Status.PASS, "Error message is not visible");
			captureScreenShot("Message");
		}
	}



}
