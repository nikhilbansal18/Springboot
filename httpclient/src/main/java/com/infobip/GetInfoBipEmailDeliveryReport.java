package com.infobip;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class GetInfoBipEmailDeliveryReport {
	
	/*
	 * private static String basicAuth(String username, String password) { return
	 * "Basic " + Base64.getEncoder().encodeToString((username + ":" +
	 * password).getBytes()); }
	 */
    
    private static String apiKeyHeader(String apiKey) {
        return "App " + apiKey;
    }
	    
    public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException, TimeoutException {
    	
        try (InputStream input = new FileInputStream("/Users/nikhilbansal/eclipse-workspace/httpclient/resources/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("API_URI_REPORTS"));
            
        	//String url = API_URI + "?messageId="  + URLEncoder.encode("1000", StandardCharsets.UTF_8);
        	//System.out.println(url);
        	HttpClient client = HttpClient.newHttpClient();
        	HttpRequest request = HttpRequest.newBuilder()
        			.GET()
                    .header("Authorization", apiKeyHeader(prop.getProperty("API_KEY")))
        			.uri(URI.create(prop.getProperty("API_URI_REPORTS")))
        			.build();
        	
        	//  .header("Authorization", basicAuth(USERNAME, PASSWORD))

    		
    		  HttpResponse<String> response= client.send(request,
    		  HttpResponse.BodyHandlers.ofString());
    		  System.out.println("Status code of the request is: " +
    		  response.statusCode()); System.out.println("Status code of the request is: "
    		  + response.body());
    		 
        	
    		/*
    		 * client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    		 * .thenApply(HttpResponse::body) .thenAccept(System.out::println) .join();
    		 */

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }  

}
