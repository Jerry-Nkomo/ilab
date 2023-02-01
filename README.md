# ilab
To execute this script you need:

1. JDK 8 or newer
2. Eclipse IDE
3. Chrome and/or Edge and/or Firefox browser

Steps to add project to Eclipse IDE:
1. Open your Eclipse
2 Click File
3. Click import
4. Expand maven
5. Select Existing Maven Projects and click Next
6. Click browse, and browse to where you downloaded the project
7. Select the project folder then click finish

Downloading Dependencies:
After importing your project you need to download dependencies
Steps:
1. In Project or Package Explorer, Right click project e.g nkomo.ilabAssessment
2. Go to Maven > click Update Project
3. Make project name e.g. nkomo.ilabAssessment is checked
4. Check the Force Update of Snapshot/Releases

To Add JUnit to your Project:
1. In Project or Package Explorer, Right click project e.g nkomo.ilabAssessment
2. Go to Build path > Click configure build path
3. Click Libraries
4. On the right of your screen/popup click Add Library
5. Select JUNit and click next
6. Preferred version is version 5, click finish after selecting version

Choosing the browser:
1. Open StepDefinition_HomePage.java file inside src/test/java/com.org.frameworkFile.stepdefinitions package
2. Go to line number 37, UIActions.browser("edge")
3. Replace edge with browser of your choice between those mentios above (Case insensitive).

Running/Executing script
1. Open TestRunner file inside src/test/java/com.org.frameworkFile.runners package
2. Do not change anything inside features because there is only one feature file
3. If you would like to run one scenario, in tags="" specify which scenario to run(@Excel or @Params)
4. If you would like to run all scenarios you can leave tags as it is
5. Right click anywhere inside TestRunner file > Run As > Click JUnit Test

NOTE:
You need to go to selenium.dev/downloads > scroll down to browsers > expand browses and download the version that matches your browser.

