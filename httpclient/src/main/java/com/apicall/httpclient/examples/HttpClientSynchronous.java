package com.apicall.httpclient.examples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientSynchronous {
	private static final String API_URI="https://jsonplaceholder.typicode.com/posts";
	
    public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException, TimeoutException {
    	
    	HttpClient client = HttpClient.newHttpClient();
    	HttpRequest request = HttpRequest.newBuilder()
    			.GET()
    			.header("accept", "application/json")
    			.uri(URI.create(API_URI))
    			.build();
    	
    	HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());
    	System.out.println("Status code of the request is: " + response.statusCode());
   
    	//parse json in objects
    	ObjectMapper mapper = new ObjectMapper();
    	List<Posts> posts = mapper.readValue(response.body(), new TypeReference<List<Posts>>() {});
    	
        posts.forEach(new Consumer<Posts>() {
			@Override
			public void accept(Posts post) {
			    System.out.println(post.toString());
			 }
		});
    }
}
