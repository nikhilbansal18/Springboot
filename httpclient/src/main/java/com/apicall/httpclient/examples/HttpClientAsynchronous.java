package com.apicall.httpclient.examples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.time.Duration;

public class HttpClientAsynchronous {
	private static final String API_URI="https://jsonplaceholder.typicode.com/posts";
	
	  private static final HttpClient httpClient = HttpClient.newBuilder()
	            .version(HttpClient.Version.HTTP_2)
	            .connectTimeout(Duration.ofSeconds(10))
	            .build();
	
    public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException, TimeoutException {
    	
    	HttpRequest request = HttpRequest.newBuilder()
    			.GET()
    			.header("accept", "application/json")
    			.uri(URI.create(API_URI))
    			.build();
    		
        CompletableFuture<HttpResponse<String>> responseAsync =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String result = responseAsync.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        System.out.println(result);


    	//parse json in objects
    	/*ObjectMapper mapper = new ObjectMapper();
    	List<Posts> posts = mapper.readValue(responseAsync.body(), new TypeReference<List<Posts>>() {});
    	
        posts.forEach(new Consumer<Posts>() {
			@Override
			public void accept(Posts post) {
			    System.out.println(post.toString());
			 }
		});*/
    }
}
