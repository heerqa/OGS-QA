package com.ogs.testrunner;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import com.ogs.automation.Browser;
import com.ogs.automation.BrowserUtils;
import com.ogs.automation.DashboardRestClient;
import com.ogs.automation.EnvSetUP;
import com.ogs.tests.UserRegistration;

public abstract class TestRunnerCommon {
	
	private static int current;
	private static int passed;
	private static int failed;
	public static UserRegistration c;
	
	//private static ChatFunctionality testrun;
	
	@Rule
    public static ErrorCollector error = null;	
	static DashboardRestClient dashboardRestClient=new DashboardRestClient();
    //static ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring/application-config.xml");

	
	public static void invokeReflectionMethod(String methodname) throws Throwable {
		Constructor[] ctors=UserRegistration.class.getDeclaredConstructors();
			Constructor ctor=null;
			for (int i = 0; i < ctors.length; i++) {
				ctor=ctors[i];
				System.out.println(ctor);
				if (ctor.getGenericParameterTypes().length==0) {
					break;
				}
			}
			
			ctor.setAccessible(true);
			
			UserRegistration c=(UserRegistration) ctor.newInstance(BrowserUtils.getInstance().getDriver());
			System.out.println("created new intance");
			try {
				c.getClass().getMethod(methodname, null).invoke(c, null);
			} catch (Throwable e) {
				error.addError(e);
			}
			
     	
	}

	public static void invokeReflectionMethodwithParam(String methodname, Object methodparam)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Constructor[] ctors=UserRegistration.class.getDeclaredConstructors();
			Constructor ctor=null;
			for (int i = 0; i < ctors.length; i++) {
				ctor=ctors[i];
				System.out.println(ctor);
				if (ctor.getGenericParameterTypes().length==0) {
					break;
				}
			}
			
			ctor.setAccessible(true);
			
			UserRegistration c=(UserRegistration) ctor.newInstance(BrowserUtils.getInstance().getDriver());
			System.out.println("created new intance");
     
     	  c.getClass().getMethod(methodname, Object[].class).invoke(c, new Object[]{new Object[]{"hi"}});
	}

	
	
	
	

	public static int getCurrent() {
		TestRunnerCommon.current++;
		return current;
	}

	public static void setCurrent(int current) {
		TestRunnerCommon.current = current;
		
	}

	public static int getPassed() {
		TestRunnerCommon.passed ++;
		return passed;
	}

	public static void setPassed(int passed) {
		TestRunnerCommon.passed = passed;
		
	}

	public static int getFailed() {
		TestRunnerCommon.failed++;
		return failed;
	}

	public static void setFailed(int failed) {
		TestRunnerCommon.failed = failed;
		
	}

   
	
	

	public static String setupDriver(){
	   	
    	String browser =EnvSetUP.getInstance().getproperties("browser") ;
    	
    	if (browser.equals("firefox")) {
    		BrowserUtils.getInstance().setDriver(Browser.Browsers.FIREFOX.browser());
			//return "firefox";
		}
    	

    	if (browser.equals("chrome")) {
    		BrowserUtils.getInstance().setDriver(Browser.Browsers.CHROME.browser());
    		//return "chrome";
		}
        
    	if (browser.equals("ie")) {
    		BrowserUtils.getInstance().setDriver(Browser.Browsers.IE.browser());
    		//return "ie";
		}
    	
    	if (browser.equals("phantomjs")) {
    		BrowserUtils.getInstance().setDriver(Browser.Browsers.PHANTOMJS.browser());
    		//return "phantomjs";
		}
    	
    	if (browser.equals("safari")) {
    		BrowserUtils.getInstance().setDriver(Browser.Browsers.SAFARI.browser());
    		//return "phantomjs";
		}
		return browser;
        
	}

    //private static GeneralUserFunctionality testrun;

    public static String getMethodName() {
    	
         return
        		
                 BrowserUtils.getInstance().getMethodName(33) + " " +
                 BrowserUtils.getInstance().getMethodName(34) + " " +
                 BrowserUtils.getInstance().getMethodName(35);
    }

    
    public static Integer beforeThisWholeTestStartsSingleUserTest(String testsuitename, int totalcount) throws Throwable {
    	Integer dashboardId=dashboardRestClient.createDashboardID(testsuitename);
    	dashboardRestClient.updateTotalCount(dashboardId, totalcount);
        //setupDriver();
        dashboardRestClient.updateBrowser(dashboardId, setupDriver());
           
        
        System.out.println("-----> LOGGING IN BEFORE TESTS START");
        BrowserUtils.getInstance().setShouldLogin(true);
        BrowserUtils.getInstance().setShouldBrowserBeClosed(false);
        //setTestrun(new ChatFunctionality(BrowserUtils.getInstance().getDriver())) ;
    
        //getTestrun().testvalidLogin();
     
        invokeReflectionMethod("testvalidLogin");
        
        
        BrowserUtils.getInstance().setShouldLogin(false);
        System.out.println("-----> DONE ");
        TestRunnerCommon.setCurrent(0);
        TestRunnerCommon.setPassed(0);
        TestRunnerCommon.setFailed(0);
		return dashboardId;
    }
    
    
    public static Integer beforeThisWholeTestStartsNoLogin(String testsuitename, int totalcount) throws Throwable {
    	Integer dashboardId=dashboardRestClient.createDashboardID(testsuitename);
    	dashboardRestClient.updateTotalCount(dashboardId, totalcount);
        //setupDriver();
        dashboardRestClient.updateBrowser(dashboardId, setupDriver());
           
        
        System.out.println("-----> LOGGING IN BEFORE TESTS START");
        BrowserUtils.getInstance().setShouldLogin(true);
        BrowserUtils.getInstance().setShouldBrowserBeClosed(false);
        //setTestrun(new ChatFunctionality(BrowserUtils.getInstance().getDriver())) ;
    
        //getTestrun().testvalidLogin();
     
        invokeReflectionMethod("dummy");
        
        
        BrowserUtils.getInstance().setShouldLogin(false);
        System.out.println("-----> DONE ");
        TestRunnerCommon.setCurrent(0);
        TestRunnerCommon.setPassed(0);
        TestRunnerCommon.setFailed(0);
		return dashboardId;
    }
    
    

    
    public static Integer beforeThisWholeTestStartsMultiUserTest(String testsuitename, int totaltest) throws Throwable {
    	Integer dashboardId=dashboardRestClient.createDashboardID(testsuitename);
    	dashboardRestClient.updateTotalCount(dashboardId, totaltest);
        //setupDriver();
        dashboardRestClient.updateBrowser(dashboardId, setupDriver());
        System.out.println("-----> LOGGING IN BEFORE TESTS START");
        BrowserUtils.getInstance().setShouldLogin(true);
        BrowserUtils.getInstance().setShouldBrowserBeClosed(false);
      // setTestrun(new ChatFunctionality(BrowserUtils.getInstance().getDriver())) ;
    
       //getTestrun().testvalidLogin();
       //BrowserUtils.getInstance().setShouldLogin(true);
        System.out.println("-----> DONE ");
        TestRunnerCommon.setCurrent(0);
        TestRunnerCommon.setPassed(0);
        TestRunnerCommon.setFailed(0);
		return dashboardId;
    }

    
    public static void afterThisWholeTestEnds() throws Throwable {
        BrowserUtils.getInstance().setShouldBrowserBeClosed(true);
        invokeReflectionMethod("browserClose");
       // getTestrun().browserClose();
    }
    
    public void afterTestRun() throws Throwable {
    	BrowserUtils.getInstance().setShouldBrowserBeClosed(true);
     	invokeReflectionMethod("browserClose");	
    	//getTestrun().browserClose();
    }
    
    public void setUpPrecondition() throws Throwable {
        System.out.println("-----> RUNNING  setUpPrecondition()");

        try {
        invokeReflectionMethod("setupPrecondition");
        	//getTestrun().setupPrecondition();
            System.out.println("++++++ PASS");
            
        }
        catch (Throwable e) {
            System.out.println("xxxxxx FAIL " + e.getMessage());
            //getTestrun().getScreenShot("setUpPrecondition");
            //error.addError(e);
        }

     }
    
    
	

}
