#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@voting
Feature: verifying vote increment

 Background:
    Given User logged into buggycars site
    
    
 @Upvoting
 Scenario Outline: Validate voting
    And User click on Lamborghini_xpath  
    And User click on LamReventon_xpath        
    And User type <comment> in comment_xpath field    
    When User submits voteSubmit_xpath button 
    Then User should see voteSubmission success message  
      
    Examples: 
      | comment                |
      | Reventon is Renventon  |  
     
 @Revoting
 Scenario: Validate user revoting
    And User click on Lamborghini_xpath  
    And User click on LamReventon_xpath
    Then Validate vote button is not visible for already voted car model by same user 
    
      
 