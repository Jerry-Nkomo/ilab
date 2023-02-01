package com.org.frameworkFiles.utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
/***************************************************
 * Class Name: GlobalVariables
 * Class Description: To have common used variables
 * Date Created: 28/01/2023
 * @author : Jerry Nkomo
 */
public class GlobalVariables {
	public static final Duration OBJ_MAX_WAIT = Duration.ofSeconds(80); 
	public static final Duration OBJ_MIN_WAIT = Duration.ofSeconds(5); 
	public static final String OBJ_SCREENSHOT_PATH = "target/images/";
	public static final String OBJ_STEPREPORT_PATH = "target/SparkReport/Spark.html";
	
	//Initialization
	public static WebDriver driver;
	public static String testScenarioName="";
	public static String featureName="";
	
	//Test Data Path
	public static final String OBJ_TESTDATASHEET="Data Dir/TestData.xlsx";
	
	//Object Initialization (Read data from Excel)
	public static com.codoid.products.fillo.Recordset objRS = null;
	
	public static boolean exitTestIteration=true;
	public static boolean takeScreenShot=true;
}
