package com.wp.steps;


import com.wp.webdriver.WebConnector;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class GenericSteps {

	 WebConnector con;
	 
	public GenericSteps(WebConnector con) {
	    this.con = con;
	}
	@Before
	public void before(Scenario s){
		System.out.println("***Before funtio***Starting the Scenario" +s.getName());
		con.initReports("Starting the scenario----"+s.getName());
		con.infoLog("***Before funtio***"+"Starting the scenario----" +s.getName());
			
	}
	@After
	public void after(){
		System.out.println("**Aft**");
		con.quit();
		
	}
		
	@Given("^User open (.*)$")
	public void openBrowser(String browserName) {
		con.openBrowser(browserName);
		con.infoLog("opening browser" + browserName);
		System.out.println("opening browser" + browserName);
	}
	
	 @And("^User navigate to (.*)$")
	 public void navigate(String url) {
		 con.infoLog("Navigating to " +url);
		 System.out.println("Navigating to " +url);
		 con.navigate(url);
	 }
	 
	 @And("^User type (.*) in (.*) field$")
	 public void type(String data, String locaterKey) throws InterruptedException {
		 con.infoLog("Typing in"+locaterKey+".Data"+data);
		 System.out.println("Typing in"+locaterKey+".Data"+data);
		 con.type(locaterKey, data);
	 }
	 
	 @And("^User click on (.*)$")
	 public void click(String locaterkey) {
		 con.infoLog("I click on "+locaterkey);
		 System.out.println("I click on "+locaterkey);
		 con.click(locaterkey);
		 System.out.println("I clicked on" +" register link");
	 }
	 
	@And("^scroll down to bottom of page$")
	public void scroll() {
		con.scrolldown();
	}
	
	
}
