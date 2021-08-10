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
@reg
Feature: Registration into Buggycars
    
    
  Scenario Outline: User registration
    Given User open <Browser>
    And User navigate to buggycarsurl
    And User click on reg_LinkText    
    And User type <UserName> in userName_id field
    And User type <FirstName> in firstName_id field
    And User type <LastName> in lastName_id field
    And User type <Password> in passWord_id field
    And User type <Password> in confirmPassword_id field
    When User submits regsubmit_xpath button    
    Then User should see the success <Message>
    Examples: 
      | Browser | UserName                    | FirstName |LastName |Password  |Message                                      |
      | Chrome  | kothaaruna2012@gmail.com    | Aruna     |Kotha    |Test@1234 |UsernameExistsException: User already exists |
      | Mozilla | test                        | an        |ks       |Test@1234 |Registration is successful                   |
      | Chrome  | just                        | ann       |kss      |Test@1234 |Registration is successful                   |
      | Chrome  | aruna1                      | aru1      |kot1     |Test@1234 | Registration is successful                   |
                   
      
