
package com.example;

import java.io.IOException;
import java.util.List;
import com.amazonaws.services.rekognition.model.Label;
import com.google.api.gax.core.CredentialsProvider;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
	static String photo;
	static List<Label> LabelDetection;
	static Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		//AWSVision obj = new AWSVision(LabelDetection, photo);
		// obj.aws(photo, "df");
		// putRequest();
	}

	// @Bean
	// public ImageAnnotatorClient imageAnnotatorClient(CredentialsProvider
	// credentialsProvider) throws IOException {
	// ImageAnnotatorSettings clientSettings = ImageAnnotatorSettings.newBuilder()
	// .setCredentialsProvider(credentialsProvider)
	// .build();
	//
	// return ImageAnnotatorClient.create(clientSettings);
	// }

	private static void putRequest() {
		// String url = "http://localhost:8082/post";
		String jsonString = new String();
		// String url =
		// "http://localhost:8081/vision?imageUrl=C:\\Users\\admin\\Pictures\\error1.jpg";
		JSONObject json = new JSONObject();
		JSONArray jsonarray1 = new JSONArray();
		String imageToAnalyze = new String();
		/*
		 * Map<String, String> params = new HashMap<>(); params.put("id", "1");
		 */
		// json.put("Description", Description);
		json.put("Description", "Score");
		jsonarray1.add(json);

		// Greeting greeting = new Greeting(1,"content");
		// MSVision vision = new MSVision(1, imageToAnalyze, jsonString);
		// User user = new User(1,"a","b");
		// user.setId(1);
		// user.setFirstName("John");
		// user.setLastName("Smith");
		//
		// RestTemplate restTemplate = new RestTemplate();
		// System.out.println("user " + vision);
		// System.out.println("RestTemplt " + restTemplate);

		// restTemplate.put(url, user, params);

		// greeting = restTemplate.getForObject(url, Greeting.class);
		// ResponseEntity<Greeting> getResponse = restTemplate.getForEntity(url,
		// Greeting.class);

		// ResponseEntity<Vision> getResponse = restTemplate.getForEntity(url,
		// Vision.class);
		// ResponseEntity<Vision> getResponse = restTemplate.postForEntity(url, vision,
		// Vision.class);

		// ResponseEntity<String> postResponse = restTemplate.postForEntity(url, vision,
		// String.class);
		// System.out.println("PostResponse " + postResponse);

		// ResponseEntity<String> getResponse1 = restTemplate.getForEntity(url,
		// String.class);
		// System.out.println("PostBody: "+postResponse.getBody());

		// System.out.println("FName " +user.getFirstName()+" LName
		// "+user.getLastName());
	}
}
// RestfulClient