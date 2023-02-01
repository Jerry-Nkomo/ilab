package com.org.frameworkFiles.stepDefinitions;

import com.org.frameworkFiles.actionKeywords.UIActions;
import com.org.frameworkFiles.pageObjects.GetInTouchPageObjects;
import com.org.frameworkFiles.utility.GlobalVariables;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/*********************************
 * Class Name: StepDefinition_HomePage Class Description: To define steps
 * relating to home page Date Created: 28/01/2023 Last Modified:01/02/2023
 * 
 * @author Jerry Nkomo
 *
 */
public class StepDefinition_HomePage extends UIActions {
	String actFeature;

	@Before
	public void initScenario(Scenario scenario) throws Exception {

		String path = scenario.getUri().toString();
		String[] featurePath = path.split("features");
		String[] feature = featurePath[1].split("\\.");
		actFeature = feature[0].substring(1);

		if (!GlobalVariables.featureName.equals(actFeature)) {
			GlobalVariables.featureName = actFeature;
			UIActions.createFeature(GlobalVariables.featureName);
		}

		UIActions.browser("edge");
	}

	@Given("^Data Set for the Scenario \"([^\"]*)\"$")
	public void data_set_for_the_scenario(String scenarioName) throws Throwable {
		// Read Data from the excel
		GlobalVariables.testScenarioName = scenarioName;
		UIActions.createFeature(actFeature);
		GlobalVariables.objRS = readDataFromExcel(GlobalVariables.featureName, scenarioName);
		UIActions.scenario(scenarioName);
	}

	@When("User is at the Home Page")
	public void launchApplication() throws Throwable {
		GetInTouchPageObjects.objects(driver);
		String url = GlobalVariables.objRS.getField("url").trim();
		GetInTouchPageObjects.launchURL(url);
	}

	@Then("User Navigates to Get In Touch Page")
	public void navigateToGetInTouchPage() throws Exception {
		GetInTouchPageObjects.objects(driver);
		click("Accept All", GetInTouchPageObjects.OBJ_AcceptAll_Button);
		click("Get In Touch Menu Link", GetInTouchPageObjects.OBJ_GetInTouch_Link);
	}

	@After
	public void endScenario() throws Exception {
		closeBrowser();
	}
}
