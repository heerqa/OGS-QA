package com.ogs.testrunner;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.ogs.automation.EnvSetUP;

public class TestUserRegistrationTests extends TestRunnerCommon {
	
	
	static int current=1;
	static int pass=1;
	static int fail=1;
	final static int total=1;
	
	@Rule
    public ErrorCollector error = new ErrorCollector();
	static final Logger logger = Logger.getLogger(TestUserRegistrationTests.class);
	static Integer dahbaordId=null;
	int dashboardID=Integer.parseInt(EnvSetUP.getInstance().getproperties("dashboardid"));
	 @BeforeClass    
	    public static void beforeThisWholeTest() throws Throwable{
		 dahbaordId=beforeThisWholeTestStartsNoLogin("Tasks Tests",total);
		 
	    }

	 	
	   @AfterClass
	    public static void afterAllTest() throws Throwable {
		   afterThisWholeTestEnds();
		   
	   }
	    
	 /*  @Before
	    public void setUpPrecondition() throws Throwable{
		  
		   super.setUpPrecondition();
	      }*/
	   
public void executeMethodGuide(String methodname ) throws Throwable{
		   
		   logger.info("-----> RUNNING " + methodname);
	        dashboardRestClient.createActivity(dashboardID,methodname );
	        
	        dashboardRestClient.updateRunningMethod(dahbaordId,methodname);
	        Integer methodid=dashboardRestClient.createMethod(dahbaordId,methodname);
	        dashboardRestClient.updatedMethodStatus(methodid, "Running");
	     	dashboardRestClient.updateCurrentCount(dahbaordId, current);
	     	dashboardRestClient.createActivity(dashboardID,current +"test is running out of"+total );
	     	current++;
	     	   
	        try {	
	        		
	        		invokeReflectionMethod(methodname);
	        		//getTestrun().verify_that_Manage_Setting_page_is_displayed_when_clicked_on_Setting_menu();
	            
	                  		

	        		dashboardRestClient.updatePassCount(dahbaordId, pass);
	        		logger.info("++++++ PASS");
	        		dashboardRestClient.createActivity(dashboardID, "++++++ PASS");

	        		dashboardRestClient.updatedMethodStatus(methodid, "Pass");
	            pass++;
	       
	        }
	        catch (Throwable  t) {
	        		fail++;
	        		System.out.println("error handeling");
	        		String errordetails=t.getMessage();
	        		System.out.println(t.getMessage());
	        		dashboardRestClient.updateFailCount(dahbaordId, fail);
	        		
	        		logger.error("xxxxxx FAIL " + errordetails);  
	        		//invokeReflectionMethodwithParam(methodname, methodname);
	        		//getTestrun().getScreenShot(methodname);
	        		error.addError(t);
	            	System.out.println("add error");
	            	dashboardRestClient.updatedMethodStatus(methodid, "Failed");
	            	System.out.println("update error method status");
	            	dashboardRestClient.updatedMethodErrorMessage(methodid, errordetails);
	            	System.out.println("update error message ");
	            	dashboardRestClient.createActivity(dashboardID,"xxxxxx FAIL  " + errordetails);
	            	System.out.println("update activity log");
	            	
	            	
	        }

		   
	   }
	  
//---------------------TESTS BELOWS--------------------------

@Test
public void userRegistraton() throws Throwable{
	executeMethodGuide("userRegistration");
	
}


}
