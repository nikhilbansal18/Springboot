package com.prop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyReader {
	
	    public  void main(String[] args) {

	        try (InputStream input = new FileInputStream("/Users/nikhilbansal/eclipse-workspace/httpclient/resources/config.properties")) {

	            Properties prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println(prop.getProperty("API_URI_LOGS"));
	            System.out.println(prop.getProperty("API_URI_SEND_MAIL"));
	            System.out.println(prop.getProperty("USERNAME"));
	            System.out.println(prop.getProperty("PASSWORD"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	    }

	}

