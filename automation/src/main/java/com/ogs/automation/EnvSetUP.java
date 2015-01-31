package com.ogs.automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
/**
 * User: Shagufta Naz
 * Date: 1/4/14
 * Time: 12:03 PM
 */
public final class EnvSetUP {
	private static EnvSetUP instance = new EnvSetUP();
	
	private static Properties prop = new Properties();
	private static InputStream input=null;
	private static OutputStream output = null;
	
	 /**
     * Singelton access.
     *
     * @return singeton instance
     */
	
	  public static EnvSetUP getInstance() {
	        return instance;
	    }

	  
	    private EnvSetUP() {
	    }


	public String getproperties(String key) {
		String value = null;
		try {
			

			String filename = "env.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
			value = prop.getProperty(key);
					
			
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public void setPropertyValue(String key, String val){
		
		try {
			System.out.println(getClass().getClassLoader().getResource("env.properties"));
			//String filename = "env.properties";
			
			PropertiesConfiguration prop;
			
				prop = new PropertiesConfiguration(getClass().getClassLoader().getResource("env.properties"));
				prop.setProperty(key, val);
				prop.save();
						
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
			}
		}
	
	
	
	
}


