package com.http.java.body;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class BodyTypes {
	
  	/*
  	 *  multipart/form-data
  	 */
  
	  public BodyPublisher ofMimeMultipartData(Map<Object, Object> data,
		      String boundary) throws IOException {
		    var byteArrays = new ArrayList<byte[]>();
		    byte[] separator = ("--" + boundary + "\r\nContent-Disposition: form-data; name=")
		        .getBytes(StandardCharsets.UTF_8);
		    for (Map.Entry<Object, Object> entry : data.entrySet()) {
		      byteArrays.add(separator);

		      if (entry.getValue() instanceof Path) {
		        var path = (Path) entry.getValue();
		        String mimeType = Files.probeContentType(path);
		        byteArrays.add(("\"" + entry.getKey() + "\"; filename=\"" + path.getFileName()
		            + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
		        byteArrays.add(Files.readAllBytes(path));
		        byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
		      }
		      else {
		        byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue() + "\r\n")
		            .getBytes(StandardCharsets.UTF_8));
		      }
		    }
		    byteArrays.add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));
		    return BodyPublishers.ofByteArrays(byteArrays);
	  }
	  
	  	/*
	  	 *  x-www-form-urlencoded
	  	 */
	  
	    // Sample: 'password=123&custom=secret&username=abc&ts=1570704369823'
	    public BodyPublisher ofFormData(Map<Object, Object> data) {
	        var builder = new StringBuilder();
	        for (Map.Entry<Object, Object> entry : data.entrySet()) {
	          if (builder.length() > 0) {
	            builder.append("&");
	          }
	          builder
	              .append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
	          builder.append("=");
	          builder
	              .append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
	        }
	        return BodyPublishers.ofString(builder.toString());
	      }
	    
	    public String appendData(Map<Object, Object> data) {
	        var builder = new StringBuilder();
	        for (Map.Entry<Object, Object> entry : data.entrySet()) {
	          if (builder.length() > 0) {
	            builder.append("&");
	          }
	          builder
	              .append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
	          builder.append("=");
	          builder
	              .append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
	        }
	        return builder.toString();
	      }
	    
}
