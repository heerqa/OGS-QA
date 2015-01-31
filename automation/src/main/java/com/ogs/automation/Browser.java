package com.ogs.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.net.URISyntaxException;

public interface Browser {
	
	String dashboardURL="http://osg-testdashboard.herokuapp.com/restapi";
	//String dashboardURL="http://localhost:8080/restapi";
    String pageloadwaittimer = "30000";

    String baseProdUrl = "https:/google.com";
    String baseDevUrl = "https:/google.com";
    public static String baseUrl = baseDevUrl;
        
    
    
    //*************************Browser Setup Below****************************************************** 
    
        
    public enum Browsers {

        SAFARI {
            public WebDriver browser() {
                WebDriver driver = new SafariDriver();
                return driver;

            }
        },

        FIREFOX {
            public WebDriver browser() {
            	/*FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.private.browsing.autostart", true);
*/
            	/*ProfilesIni profile = new ProfilesIni();
            	FirefoxProfile ffprofile = profile.getProfile("default");
            	
            	WebDriver driver = new FirefoxDriver(ffprofile);*/
            	WebDriver driver = new FirefoxDriver();
            	//WebDriver driver = new HtmlUnitDriver();
            	return driver;

            }
        },
        CHROME {
            public WebDriver browser() {
            	
            	java.net.URL resource = Browser.class.getResource("/chromedriver.exe");
            	
      		  	String path;
				try {
					path = new File(resource.toURI()).getAbsolutePath();
					System.setProperty("webdriver.chrome.driver", path);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				ChromeOptions options = new ChromeOptions();
            	//options.addArguments("--no-sandbox");
            	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				
      		   //-- TODO: get working in UNIX
                WebDriver driver = new ChromeDriver(capabilities);
                return driver;

            }
        },
        IE {
            public WebDriver browser() {
            	
            	java.net.URL resource = Browser.class.getResource("/IEDriverServer.exe");
            	
      		  	String path;
				try {
					path = new File(resource.toURI()).getAbsolutePath();
					System.setProperty("webdriver.ie.driver", path);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
								
                WebDriver driver = new InternetExplorerDriver();
                return driver;

            }
        },
        PHANTOMJS {
            public WebDriver browser() {
                // prepare capabilities
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                // not really needed: JS enabled by default
                desiredCapabilities.setJavascriptEnabled(true);
                desiredCapabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

                String phantomjsRelativeLocation = "phantomjs/1.9.2/bin/phantomjs" + FileUtils.getOsVersionStringExtension();
                File phantomJS = FileUtils.prepateExecutableResourceFile(this.getClass(), phantomjsRelativeLocation);
                String phantomjsAbsoluteLocation = phantomJS.getPath();
                desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjsAbsoluteLocation);
                // Launch driver (will take care and ownership of the phantomjs process)
                WebDriver driver = new PhantomJSDriver(desiredCapabilities);
                return driver;
            }
        };

        public abstract WebDriver browser();
    }
}
