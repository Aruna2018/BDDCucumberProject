package com.wp.steps;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun=false,
		strict=true,
		monochrome=false,
		features= {"src/test/resources/"},
		glue = {"com.wp.steps"},
		plugin= {
				"html:target/site/cucumber-html",
				"json:target/cucumber1.json"}
		
		)


public class Runner {

}
