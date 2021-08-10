package com.wp.steps;

import java.util.Map;



import com.wp.webdriver.WebConnector;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.assertj.core.api.Assertions.assertThat;



public class ApplicationSpecificSteps {

	WebConnector con;
	
	public ApplicationSpecificSteps(WebConnector con) {
		
		this.con =con;
	}
	
	@When("^User submits (.*) button$")
	public void clickOnRegSubmit(String locaterkey) {
		//System.out.println("This is submit");
		con.infoLog("I click on "+locaterkey);
		 System.out.println("I click on "+locaterkey);
		 con.click(locaterkey);
	}
	 
	 @Then("^User should see the success (.*)$")
	 public void validateReg(String expectedResult) {
	    	System.out.println("this is validate error");
	    	con.validateRegis(expectedResult);
	 }
	    
	 @Then("^login should be (.*)$")
	 public void validatingLogin(String result) {
		 con.validateLogin(result);
	 }
	
	 @Given("^User logged into buggycars site$")
	 public void loginintoBuggyCars() {
		con.loginbuggycars();
	 }
	 @Then("^Validate vote button is not visible for already voted car model by same user$")
	 public void validateRevoting() {
		con.validateRevote();
	 }
	  @Then("^User should see voteSubmission success message$")
	  public void validate_vote_submission() {
		 con.validateVoteSubmission();
	  }
	 
	  @And("^User validates vote count increment$")
	  public void validate_votecount() {
		  con.validateVoteCountIncrement();
	  }
	
}
