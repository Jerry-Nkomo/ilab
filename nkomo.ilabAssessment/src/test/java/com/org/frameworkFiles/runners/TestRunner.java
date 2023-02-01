package com.org.frameworkFiles.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.org.frameworkFiles.actionKeywords.UIActions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/*********************************
 * Class Name: TestRunner
 * Class Description: To run or execute test script
 *  Date Created: 28/01/2023
 * @author Jerry Nkomo
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/ilab.feature" }, 
glue = {"com.org.frameworkFiles.stepDefinitions" },
tags = "", plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class TestRunner {
	@BeforeClass
	public static void intiReport() throws Exception {
		UIActions.createReport();
	}

	@AfterClass
	public static void endTest() throws Exception {
		UIActions.report();
	}
}
