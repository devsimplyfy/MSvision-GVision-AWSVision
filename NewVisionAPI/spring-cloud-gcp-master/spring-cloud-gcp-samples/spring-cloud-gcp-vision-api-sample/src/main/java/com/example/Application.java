/*
 *  Copyright 2017 original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example;

import java.io.IOException;

import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author João André Martins
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//putRequest();
	}

	
	@Bean
	public ImageAnnotatorClient imageAnnotatorClient(CredentialsProvider credentialsProvider) throws IOException {
		ImageAnnotatorSettings clientSettings = ImageAnnotatorSettings.newBuilder()
				.setCredentialsProvider(credentialsProvider)
				.build();

		return ImageAnnotatorClient.create(clientSettings);
	}
	
	private static void putRequest() {
		String url = "http://localhost:8082/post";
		String imageUrl = null;
		//String url = "http://localhost:8081/vision?imageUrl=C:\\Users\\admin\\Pictures\\error1.jpg";
		JSONObject json = new JSONObject();
		JSONArray jsonarray1 = new JSONArray();
		/*
		 * Map<String, String> params = new HashMap<>(); params.put("id", "1");
		 */
		//json.put("Description", Description);
		json.put("Description", "Score");
		jsonarray1.add(json);
		
		// Greeting greeting = new Greeting(1,"content");
		Vision vision = new Vision(1,jsonarray1,imageUrl);
		// User user = new User(1,"a","b");
		// user.setId(1);
		// user.setFirstName("John");
		// user.setLastName("Smith");

		RestTemplate restTemplate = new RestTemplate();
		System.out.println("user " + vision);
		System.out.println("RestTemplt " + restTemplate);

		// restTemplate.put(url, user, params);

		// greeting = restTemplate.getForObject(url, Greeting.class);
		// ResponseEntity<Greeting> getResponse = restTemplate.getForEntity(url,
		// Greeting.class);
		
		//ResponseEntity<Vision> getResponse = restTemplate.getForEntity(url, Vision.class);
		//ResponseEntity<Vision> getResponse = restTemplate.postForEntity(url, vision, Vision.class);
		
		//ResponseEntity<String> postResponse = restTemplate.postForEntity(url, vision, String.class);
		//System.out.println("PostResponse  " + postResponse);
		
		//ResponseEntity<String> getResponse1 = restTemplate.getForEntity(url, String.class);
		//System.out.println("PostBody: "+postResponse.getBody());
		
		// System.out.println("FName " +user.getFirstName()+" LName
		// "+user.getLastName());
	}
}
//RestfulClient