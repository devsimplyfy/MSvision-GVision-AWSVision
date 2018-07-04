
package com.example;

import java.io.IOException;

import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ImageAnnotatorClient imageAnnotatorClient(CredentialsProvider credentialsProvider) throws IOException {
		ImageAnnotatorSettings clientSettings = ImageAnnotatorSettings.newBuilder()
				.setCredentialsProvider(credentialsProvider).build();

		return ImageAnnotatorClient.create(clientSettings);
	}
}
