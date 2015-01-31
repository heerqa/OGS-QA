package com.ogs.automation;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DashboardRestClient {
	static final Logger logger = Logger.getLogger(DashboardRestClient.class);
	
	RestTemplate restTemplate=new RestTemplate();
	
	public int createDashboardID(String testName) throws JsonProcessingException, IOException{
		
		ResponseEntity<String> startTest=restTemplate.postForEntity(Browser.dashboardURL+"/createdashboard/"+testName, null,String.class);
		System.out.println(startTest.getStatusCode());
		ObjectMapper objectMapper=new ObjectMapper();
		if (startTest.getStatusCode()==HttpStatus.OK) {
			JsonNode rootNode=objectMapper.readTree(startTest.getBody());
			JsonNode idNode=rootNode.path("id");
			System.out.println("dashboard before test start"+ EnvSetUP.getInstance().getproperties("dashboardid"));
			 EnvSetUP.getInstance().setPropertyValue("dashboardid", String.valueOf(idNode.toString()));
			 System.out.println("dashboard after test start"+ EnvSetUP.getInstance().getproperties("dashboardid")); 
		    
			return Integer.parseInt(idNode.toString());
		} else {
			logger.info("Unable to establish connection with dashboard");
			return (Integer) null;
		}
			
	}
	
	
	public int createActivity(Integer dashboardID,String currentactivity) throws JsonProcessingException, IOException{
		
		ResponseEntity<String> startTest=restTemplate.postForEntity(Browser.dashboardURL+"/activity/"+dashboardID+"/"+currentactivity, null,String.class);
		System.out.println(startTest.getStatusCode());
		ObjectMapper objectMapper=new ObjectMapper();
		if (startTest.getStatusCode()==HttpStatus.OK) {
			JsonNode rootNode=objectMapper.readTree(startTest.getBody());
			JsonNode idNode=rootNode.path("id");
			return Integer.parseInt(idNode.toString());
		} else {
			logger.info("Unable to establish connection with dashboard");
			return (Integer) null;
		}
			
	}
	
public int createMethod(Integer dashboardID,String methodname) throws JsonProcessingException, IOException{
		
		ResponseEntity<String> startTest=restTemplate.postForEntity(Browser.dashboardURL+"/createdmethod/"+dashboardID+"/"+methodname, null,String.class);
		System.out.println(startTest.getStatusCode());
		ObjectMapper objectMapper=new ObjectMapper();
		if (startTest.getStatusCode()==HttpStatus.OK) {
			JsonNode rootNode=objectMapper.readTree(startTest.getBody());
			JsonNode idNode=rootNode.path("id");
			idNode.toString();
			return Integer.parseInt(idNode.toString());
		} else {
			logger.info("Unable to establish connection with dashboard");
			return (Integer) null;
		}
			
	}

public void updateBrowser(Integer dashboardId, String browserName){
	restTemplate.put(Browser.dashboardURL+"/updatebrowser/"+dashboardId+"/"+browserName, null , String.class);
	
}

public void updateTotalCount(Integer dashboardId, Integer totalcount){
	restTemplate.put(Browser.dashboardURL+"/updatetotalcount/"+dashboardId+"/"+totalcount, null , String.class);
	
}

public void updateRunningMethod(Integer dashboardId, String running_method_name){
	restTemplate.put(Browser.dashboardURL+"/updaterunningmethod/"+dashboardId+"/"+running_method_name, null , String.class);
	
}

public void updateCurrentCount(Integer dashboardId, Integer currentcount){
	restTemplate.put(Browser.dashboardURL+"/updatecurrentcount/"+dashboardId+"/"+currentcount, null , String.class);
}


public void updatePassCount(Integer dashboardId, Integer passcount){
	restTemplate.put(Browser.dashboardURL+"/updatepasscount/"+dashboardId+"/"+passcount, null , String.class);
}

public void updateFailCount(Integer dashboardId, Integer failcount){
	restTemplate.put(Browser.dashboardURL+"/updatefailcount/"+dashboardId+"/"+failcount, null , String.class);
}

public void updatedMethodStatus(Integer methodid, String status){
	restTemplate.put(Browser.dashboardURL+"/updatedmethodstatus/"+methodid+"/"+status, null , String.class);
	
}

public void updatedMethodErrorMessage(Integer methodid, String errormessage){
	restTemplate.put(Browser.dashboardURL+"/updatedmethoderrormessage/"+methodid+"/"+errormessage, null , String.class);
	
}


}
