Feature: ilab
  I want to use this template for my feature file

@Excel
  Scenario: Submit Application With Excel
    Given Data Set for the Scenario "Submit Application With Excel"
    When User is at the Home Page
    And User Navigates to Get In Touch Page
    And User Proceeds to Careers
    Then User Inputs Excel Data
    
    @Params
    Scenario Outline: Submit Application With Parametization
    Given Data Set for the Scenario "Submit Application With Parametization"
    When User is at the Home Page
    And User Navigates to Get In Touch Page
    And User Proceeds to Careers
    Then User Inputs "<Name>" And "<Email>"
    Examples:
|Name|Email|
|Jerry|automationAssessment@iLABQuality.com|
    
