package com.ogs.tests;

import java.io.IOException;



import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


import com.ogs.automation.DashboardRestClient;
import com.ogs.automation.EnvSetUP;

public class UserRegistration extends CommonMethods {
	
	
	public UserRegistration(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	
	@Rule
    public ErrorCollector collector = new ErrorCollector();
	static final Logger logger = Logger.getLogger(UserRegistration.class);
    DashboardRestClient dashboardRestClient=new DashboardRestClient();
    int dashboardID=Integer.parseInt(EnvSetUP.getInstance().getproperties("dashboardid"));
    
    
    public void logger(String message){
  	  try {
		dashboardRestClient.createActivity(dashboardID, message);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	  logger.info(message);
  		
    }
    
    public void userRegistration() throws Throwable{
    	 String baseurl =EnvSetUP.getInstance().getproperties("baseurl") ;
    	 driver.get(baseurl);
    	 
    	 
    	 String email="email"+uniqueval()+"@yopmail.com";
    	 String username="username"+uniqueval();
    	System.out.println(username);
    	logger("Click on Signup button");
    	waitForElementToLoadandClick(By.xpath("(//a[contains(text(),'Sign Up')])[3]"), 5);
    	 logger("Enter email");
    	    waitForElementToLoadandSendKeys(By.id("email"), email, 12);
    	    
    	    logger("enter username");
    	    waitForElementToLoadandSendKeys(By.id("username"), username, 12);
    	    
    	    logger("Enter password as At12345678");
    	    waitForElementToLoadandSendKeys(By.id("password"), "At12345678", 12);
    	    logger("Renter password as At12345678");
    	    waitForElementToLoadandSendKeys(By.id("repassword"), "At12345678", 12);
    	    
    	    new Select(driver.findElement(By.xpath("//html[@id='ls-global']/body/div[2]/div/div/div/div/div/div/div/div[2]/div/form/div/div[4]/div/div/div[2]/span/select"))).selectByVisibleText("AB");    	    
    	    
    	    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    	    driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
    	    driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
    	    driver.findElement(By.xpath("(//a[contains(text(),'Sign Up')])[2]")).click();
    	    Thread.sleep(10000);
    	    waitForElementToLoad(By.xpath("//html[@id='ls-global']/body/div[2]/div[2]/div/div/div/div/div/div/h2"), 15);
    	    System.out.println(driver.findElement(By.xpath("//html[@id='ls-global']/body/div[2]/div[2]/div/div/div/div/div/div/h2")).getText());
    	    Assert.assertEquals("SUCCESS", driver.findElement(By.xpath("//html[@id='ls-global']/body/div[2]/div[2]/div/div/div/div/div/div/h2")).getText());
    	    
    	    //Assert.assertEquals("SU", driver.findElement(By.xpath("//html[@id='ls-global']/body/div[2]/div[2]/div/div/div/div/div/div/h2")).getText());	
			
    	    
    }

}
