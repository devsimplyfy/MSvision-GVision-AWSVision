package com.example;

import java.util.List;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.handlers.HandlerContextKey;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.LabelDetection;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.TextDetection;

public class AWSVision {

	List<Label> LabelDetection = null;
	String photo = "multijeans.jpeg";

	public AWSVision(List<Label> LabelDetection, String Photo) {
		this.photo = photo;
		this.LabelDetection = LabelDetection;
	}

	// public static void main(String[] args) throws Exception {
	public AWSVision aws(List<Label> LabelDetection, String photo) throws Exception {

		photo = "multijeans.jpeg";
		String bucket = "instantweb2";

		AWSCredentials credentials;
		try {
			credentials = new ProfileCredentialsProvider("myProfile").getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (/Users/userid/.aws/credentials), and is in a valid format.", e);
		}

		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion(Regions.US_WEST_2)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		// DetectTextRequest request = new DetectTextRequest().withImage(new
		// Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)));
		System.out.println(HandlerContextKey.class.getProtectionDomain().getCodeSource().getLocation().getPath());

		DetectLabelsRequest request = new DetectLabelsRequest()
				.withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)));
		
		DetectLabelsRequest request1 = new DetectLabelsRequest()
				.withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)));
		
		

		try {
			// DetectTextResult result = rekognitionClient.detectText(request);
			DetectLabelsResult result = rekognitionClient.detectLabels(request);
			// List<TextDetection> textDetections = result.getTextDetections();
			LabelDetection = result.getLabels();

			System.out.println("Detected lines and words for " + photo);
			// for (TextDetection text : textDetections) {
			// System.out.println("Detected: " + text.getDetectedText());
			// System.out.println("Confidence: " + text.getConfidence().toString());
			// System.out.println("Id : " + text.getId());
			// System.out.println("Parent Id: " + text.getParentId());
			// System.out.println("Type: " + text.getType());
			// System.out.println();
			// }
			System.out.println(LabelDetection);
			for (Label text : LabelDetection) {
				System.out.println("Name: " + text.getName());
				System.out.println("Confidence: " + text.getConfidence());
				System.out.println("Class: " + text.getClass());

			}
		} catch (AmazonRekognitionException e) {
			e.printStackTrace();
		}
		return null;
	}

}