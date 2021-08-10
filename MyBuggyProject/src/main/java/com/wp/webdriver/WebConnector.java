package com.wp.webdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.wp.reports.ExtentManager;
import static org.assertj.core.api.Assertions.*;

public class WebConnector {
	
	WebDriver driver;
	
	 public String name;
	 public Properties prop;
	 public ExtentReports rep;
	 public ExtentTest scenario;
	 
	
	
	public WebConnector() {
		//name = "A";
		if(prop==null) {
			
			try {
				prop = new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties");
				prop.load(fs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

	
	public void openBrowser(String browserName) {
		if(browserName.equalsIgnoreCase("Mozilla")) {
			//System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
			driver =new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\NewDrivers\\chromedriver_win32\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("IE")) {
			driver=new InternetExplorerDriver();
		}
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.manage().window().maximize();
			infoLog("opened Browser" +browserName);
		}
	  
	public void navigate(String urlkey) {
		System.out.println(prop.getProperty(urlkey));
		driver.get(prop.getProperty(urlkey));
		
	}
	public void click(String objectKey) {
		//driver.findElement(By.xpath(prop.getProperty(objectKey))).click();
		getObject(objectKey).click();
	}
	public void type(String objectKey, String data) throws InterruptedException {
		//driver.findElement(By.xpath(prop.getProperty(objectKey))).sendKeys(data);
		waitTime();
		
		getObject(objectKey).sendKeys(data);
	}

	//central function to extract the objects
	public WebElement getObject(String objectKey){
		System.out.println("this is objectKey"+ objectKey);
		WebElement  e = null;
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		try {
			if(objectKey.endsWith("_xpath")){
		       e = driver.findElement(By.xpath(prop.getProperty(objectKey)));		
		      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(objectKey))));
			}else if(objectKey.endsWith("_id")) {
				 e = driver.findElement(By.id(prop.getProperty(objectKey)));		
			      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(prop.getProperty(objectKey))));
			}else if(objectKey.endsWith("_LinkText")) {
				 e = driver.findElement(By.linkText(prop.getProperty(objectKey)));		
			      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(prop.getProperty(objectKey))));
			}else if(objectKey.endsWith("_css")){
				 e = driver.findElement(By.cssSelector(prop.getProperty(objectKey)));		
			      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(prop.getProperty(objectKey))));
			}else if(objectKey.endsWith("_name")){
				 e = driver.findElement(By.cssSelector(prop.getProperty(objectKey)));		
			      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(prop.getProperty(objectKey))));
			}
			
		}catch(Exception ex) {
			//report the failure
			ex.getMessage();
			ex.printStackTrace();
			reportFailure("Unable to extract object "+objectKey);
		}
		
		return e;
	}

    public boolean isElementPresent(String objectKey) {
    	List<WebElement> e =null;    	
          WebDriverWait wait = new WebDriverWait(driver,60);
		
		try {
			if(objectKey.endsWith("_xpath")){
		         e = driver.findElements(By.xpath(prop.getProperty(objectKey)));		
		         wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(objectKey))));
			}else if(objectKey.endsWith("_id")) {
				 e = driver.findElements(By.id(prop.getProperty(objectKey)));		
			      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(prop.getProperty(objectKey))));
			}else if(objectKey.endsWith("_name")) {
				 e = driver.findElements(By.name(prop.getProperty(objectKey)));		
			      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(prop.getProperty(objectKey))));
			}else if(objectKey.endsWith("_css")){
				 e = driver.findElements(By.cssSelector(prop.getProperty(objectKey)));		
			      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(prop.getProperty(objectKey))));
			}
			
			
			
		}catch(Exception ex) {
			//report the failure
			ex.getMessage();
			ex.printStackTrace();
		}
		if(e.size()==0)
			return false;
		else
		    return true;
		
		

    }
    
    public void validateLogin(String expectedResult) {
    	boolean result = isElementPresent("logoutButton_xpath");//send the webelementkey
    	String actualResult ="";
    	if(result) {
    		actualResult ="success";
    	}else {
    			actualResult= "failure";
    	}
    	infoLog("ExpectedResult was "+ expectedResult);
    	infoLog("Got actual Result as "+ actualResult);
    	
    	if(!expectedResult.equals(actualResult)) {
    		System.out.println("failure");
    		reportFailure("Scenario Failure");// reporting failure
    	}
    		
    }
    
    
    public void validateRegis(String expResult) {
    	 WebElement e = getObject("successMessage_xpath");
    	String actualResult= e.getText().trim();
    	String expectedResult= expResult.trim();
    	infoLog("Expected success message"+ expectedResult);
    	infoLog("Actual success message" + actualResult);
    	if(!expectedResult.equalsIgnoreCase(actualResult)) {
    		System.out.println("failure");//reporting failure
    		reportFailure("Registration scenario Failure");
    	}
    	
    	System.out.println("this is actual result"+ actualResult);
    	infoLog("Registration success");
    }
    public void loginbuggycars() {
    	String browser = prop.getProperty("browser");
    	
    	openBrowser(browser);
    	navigate("buggycarsurl");
    	
    	System.out.println("typing inside the username field");
    	 
	      try {
			type("loginUsername_xpath", prop.getProperty("username"));
			 type("loginPassword_xpath", prop.getProperty("password"));
			 click("loginButton_xpath");
		} catch (InterruptedException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
    }
    public void validateRevote(){
    	infoLog("validating  revoting ");
       boolean result=  isElementPresent("votedMessage_xpath");
       if(result!=true) {
    	   reportFailure("Element doesn't exist");
       }
       infoLog("validation success" + "Thank you for your vote!   "+ "Message been shown");
    }
    
    public void validateVoteSubmission(){
    	
    	infoLog("validating  revoting ");
        boolean result=  isElementPresent("votedMessage_xpath");
        if(result!=true) {
     	   reportFailure("Element doesn't exist");
        }
        infoLog("validation success" + "Thank you for your vote!   "+ "Message been shown");
     
    }
    
    public void validateVoteCountIncrement(){
    	WebElement e =  getObject("votecount_xpath");
    	String voteActual = e.getText();
    	System.out.println(voteActual);
    }
	public void login(String username, String password) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("typing inside the username field");
	     // type("username_xpath", username);
	      //type("password_xpath", password);
		 
	}
	
	public void scrolldown(){
		System.out.println("scrolling down to bottom of the page");
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
	}
	public void switchwindow(String expectedUrl){
		Set <String> set = driver.getWindowHandles();
	      Iterator<String> it = set.iterator();
	      String parentWindowId = it.next();
	      String childWindowId = it.next();
	      System.out.println(set);
	      driver.switchTo().window(childWindowId);
	      String actualUrl =geturl();
	      infoLog("This is the expectedUrl--" + expectedUrl);
	      infoLog("This is the actualUrl--"+ actualUrl);	      
	      //assertThat(expectedUrl).isEqualTo(actualUrl);	
	      assertThat(expectedUrl).isEqualTo(actualUrl);
	      driver.close();
	      driver.switchTo().window(parentWindowId);
	}	
	public void acceptAlertIfPresent(){
		try {
		WebDriverWait  wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());
		
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		}catch(Exception e) {
			
		}
	}
	public void waitTime() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public String geturl(){
	 String url = driver.getCurrentUrl();
	 return url;
	}
	//***************** logging **********************//
	public void infoLog(String msg) {
		scenario.log(Status.INFO, msg);
	}
	
	public void reportFailure(String errMsg) {
		//fail in the extent reports
		scenario.log(Status.INFO, errMsg);
		
		//take screenshot and put in reports
		takeScreenShot();
		//fail in cucumber as well
	   assertThat(false);
	
	}
	public void takeScreenShot(){
		
		//fileName of the screenshot
		
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_")+".png";
		//take screenshot
		File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			//get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
		    
			//put screehshot file in reports
			scenario.log(Status.FAIL, "Screenshot--> "+scenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//*******************Reporting*********************//
	public void quit() {
		if(rep!=null)
			rep.flush();
		driver.close();
		
	}
	public void initReports(String ScenarioName) {
		
		rep = ExtentManager.getInstance(prop.getProperty("reportPath"));
		scenario  = rep.createTest(ScenarioName);
		scenario.log(Status.INFO, "starting"+ ScenarioName);
		
	}
	

}
