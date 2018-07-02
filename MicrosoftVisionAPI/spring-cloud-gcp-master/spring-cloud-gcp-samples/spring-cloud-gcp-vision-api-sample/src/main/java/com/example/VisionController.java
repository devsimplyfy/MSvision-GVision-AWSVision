
package com.example;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.*;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.log4j.Logger;

@SuppressWarnings("unused")
@RestController
public class VisionController {

	final String ROOT_URI = "http://localhost:8080";
	Logger logger = Logger.getLogger(VisionController.class);
	JSONObject json = new JSONObject();
	JSONArray jsonarray = new JSONArray();
	String jsonString = new String();

	// String imageToAnalyze =
	// "https://upload.wikimedia.org/wikipedia/commons/1/12/Broadway_and_Times_Square_by_night.jpg";
	String imageToAnalyze;// = "C:\\Users\\admin\\Pictures\\error1.jpg";

	@SuppressWarnings("unchecked")
	@RequestMapping("/msvision")
	public MSVision vision(String imageToAnalyze1) throws Exception {
		File file1 = new File("C:\\Users\\admin\\Documents\\MicrosoftVisionAPI\\subscriptionKey.txt");

		String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze";
		String subscriptionKey = FileUtils.readFileToString(file1, "UTF-8");
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		URIBuilder builder = new URIBuilder(uriBase);

		// Request parameters. All of them are optional.
		builder.setParameter("visualFeatures", "Categories,Description,Color");
		builder.setParameter("language", "en");

		// Prepare the URI for the REST API call.
		URI uri = builder.build();
		HttpPost request = new HttpPost(uri);

		String imgIndex = imageToAnalyze1.substring(0, 4);
		ByteBuffer imageBytes;
		if (imgIndex == "http") {
			URL url = new URL(imageToAnalyze1);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Firefox");
			try (InputStream inputStream = conn.getInputStream()) {
				int n = -1;
				byte[] buffer = new byte[1024];
				while (0 != (n = inputStream.read(buffer))) {
					baos.write(buffer, 0, n);
				}
			}

			byte[] img = baos.toByteArray();

			// Request headers.
			// request.setHeader("Content-Type", "application/octet-stream"); // For Local
			// File System Image
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
			request.setEntity(new ByteArrayEntity(img));

		} else {
			Path path = Paths.get(imageToAnalyze);
			// ByteString imageBytes2 = ByteString.readFrom(new FileInputStream(fPath));
			byte[] img = Files.readAllBytes(path);
			request.setHeader("Content-Type", "application/octet-stream"); // For Local File System Image
			// request.setHeader("Content-Type", "application/json");//For Httpurl
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
			request.setEntity(new ByteArrayEntity(img));
			// request.setEntity(requestEntity);
		}

		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();

		// if (entity != null) {
		if (entity != null) {
			// Format and display the JSON response.
			// String jsonString = EntityUtils.toString(entity);
			jsonString = EntityUtils.toString(entity);
			System.out.println("REST Response:\n");
			System.out.println("JsonString " + jsonString);
		}
		logger.info("EntityResponse Displayed");
		System.out.println(jsonString);

		// return new ComputerVision(1,json,imageToAnalyze);
		return new MSVision(91, imageToAnalyze1, jsonString);

	}

	public MSVision vision() {
		return new MSVision(9, imageToAnalyze, jsonString);
	}

	/* AWS VisionAPI */
	String photo;
	List<Label> LabelDetection;
	private Object resourceLoader;

	@RequestMapping("/awsvision")
	public AWSModel awsvision3(String imagePath, String LDetect) throws Exception {

		String imgIndex = imagePath.substring(0, 4);
		String http1 = "http";
		ByteBuffer imageBytes;
		if (imgIndex.equals(http1)) {
			URL url = new URL(imagePath);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Firefox");
			try (InputStream inputStream = conn.getInputStream()) {
				int n = -1;
				byte[] buffer = new byte[1024];
				while (0 != (n = inputStream.read(buffer))) {
					baos.write(buffer, 0, n);
				}
			}

			byte[] img = baos.toByteArray();
			imageBytes = ByteBuffer.wrap(img);
		} else {
			try (InputStream inputStream = new FileInputStream(new File(imagePath))) {
				imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
			}
		}

		AWSCredentials credentials = new ProfileCredentialsProvider("myProfile").getCredentials();
		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion(Regions.US_WEST_2)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		DetectLabelsRequest request = new DetectLabelsRequest().withImage(new Image().withBytes(imageBytes))
				.withMaxLabels(10).withMinConfidence(77F);
		try {

			DetectLabelsResult result = rekognitionClient.detectLabels(request);
			List<Label> labels = result.getLabels();

			System.out.println("Detected labels for " + imagePath);
			for (Label label : labels) {

				LDetect = labels.toString();
			}

		} catch (AmazonRekognitionException e) {
			e.printStackTrace();
		}
		return new AWSModel(imagePath, LDetect);

	}

}
