package com.viber;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.http.java.body.BodyTypes;

public class SendViberMessage {
	
	/*
	 * private static String basicAuth(String username, String password) { return
	 * "Basic " + Base64.getEncoder().encodeToString((username + ":" +
	 * password).getBytes()); }
	 */
    
    private static String apiKeyHeader(String apiKey) {
        return "App " + apiKey;
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
    	
        try (InputStream input = new FileInputStream("/Users/nikhilbansal/git/Springboot/httpclient/resources/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("API_URI_SEND_MAIL"));
        	BodyTypes bodyType = new BodyTypes();
            String boundary = new BigInteger(256, new Random()).toString();

            // form parameters
            Map<Object, Object> data = new HashMap<>();
            data.put("messageId", "1001");
            data.put("from", "nikhilbansal18@selfserviceib.com");
            data.put("to", "nikhilbansal1812@gmail.com");
            data.put("subject", "Java Test mail from InfoBip");
            data.put("text", "java text body mail from InfoBip ApiKey 1");
            data.put("trackclicks", true);
            data.put("trackopens", true);
            data.put("track", true);

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(bodyType.ofMimeMultipartData(data, boundary))
        			.uri(URI.create(prop.getProperty("API_URI_SEND_MAIL")))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                    .header("X-Viber-Auth-Token", "4bad66318567deae-4aaebdb7dbfcce2e-1bb9d1a4f4f1c1ba")
                    .header("Content-Type", "multipart/form-data;boundary=" + boundary)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // print status code
            System.out.println(response.statusCode());
            // print response body
            System.out.println(response.body());

        } catch (IOException ex) {
            ex.printStackTrace();
        }	

    }

}