package com.ogs.tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

import com.ogs.automation.BrowserUtils;
import com.ogs.automation.EnvSetUP;
import com.ogs.automation.FileUtils;
import com.ogs.automation.TestData;

public class CommonMethods {
	
	public  WebDriver driver;
	
    public CommonMethods(WebDriver driver) {
		super();
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("time has been set for driver");
	}


	/*public CommonMethods(WebDriver e) {
        this.driver = e;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }*/

    
	TestData testData=new TestData();

	public boolean ifElementPresent(By by)throws Throwable{
        try {
            driver.findElement(by);
            return true;
        }
        catch (Throwable t) {
            return false;
        
        }
    }
	
	
	public void waitForElementToLoadandClick(By by, int counter) throws Throwable {
		int timeCounter = 1;
		boolean timeout = true;
		do {
		    Thread.sleep(2000);
		    timeCounter++;
		   System.out.println("Page is still loading. The couner is = "+timeCounter);
		    if (ifElementPresent(by)|| timeCounter > counter) {
		    	 driver.findElement(by).click();
		        timeout = false;
		    }
		}
		while (timeout);
	}
	
	public void waitForElementToLoad(By by, int counter) throws InterruptedException, Throwable {
		int timeCounter = 1;
		boolean timeout = true;
		do {
		    Thread.sleep(4000);
		    timeCounter++;
		   System.out.println("Page is still loading. The couner is = "+timeCounter);
		    if (ifElementPresent(by)|| timeCounter > counter) {
		    
		        timeout = false;
		    }
		}
		while (timeout);
	}
	
	public void waitForElementToLoadandSendKeys(By by, String sendvlaues, int counter) throws InterruptedException, Throwable {
		int timeCounter = 1;
		boolean timeout = true;
		do {
		    Thread.sleep(2000);
		    timeCounter++;
		   System.out.println("Page is still loading. The couner is = "+timeCounter);
		    if (ifElementPresent(by)|| timeCounter > counter) {
		    	driver.findElement(by).clear();
		    	 driver.findElement(by).sendKeys(sendvlaues);
		        timeout = false;
		    }
		}
		while (timeout);
	}
	
	public String uniqueval(){
		
		String date =new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return date;
	}

	
	
	
	public void getScreenShot(String screenshotname) throws Throwable{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=FileUtils.getUserDir();
		System.out.println(path);
	    org.apache.commons.io.FileUtils.copyFile(scrFile, new File(path+"/target/ScreensonError/"+screenshotname+".png"));
	 
				
	}
	
	public void browserClose() {
		System.out.println("going to close the browser");
		driver.quit();
		
		
	}

	public void testvalidLogin() throws Throwable {
        if (BrowserUtils.getInstance().isShouldLogin()) {
        	testLogintoOGS();
        }
    }
	
	public void clickonHomeLink() throws Throwable{
		
		waitForElementToLoadandClick(By.cssSelector(".dir"), 5);
	}

  public void testLogintoOGS() throws Throwable{
	 
	  String baseurl =EnvSetUP.getInstance().getproperties("baseurl") ;
	  String username =EnvSetUP.getInstance().getproperties("username") ;
	  String password=EnvSetUP.getInstance().getproperties("password") ;
	  driver.get(baseurl);
	 
	  driver.findElement(By.id("Username")).click();
	    driver.findElement(By.id("Username")).clear();
	    driver.findElement(By.id("Username")).sendKeys(username);
	    driver.findElement(By.id("Password")).clear();
	    driver.findElement(By.id("Password")).sendKeys(password);
	    driver.findElement(By.linkText("GO")).click();
	  Thread.sleep(2000);
		  
  }


  public void setupPrecondition() throws Throwable{
	  driver.navigate().refresh();
	  
  }
  
  public void dummy() throws Throwable{
	  
  }
  
	
}
