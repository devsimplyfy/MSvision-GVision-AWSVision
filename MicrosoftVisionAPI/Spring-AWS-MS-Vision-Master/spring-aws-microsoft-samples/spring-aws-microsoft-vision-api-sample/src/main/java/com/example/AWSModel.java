package com.example;

import java.util.List;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.rekognition.model.Label;

@SuppressWarnings("unused")
public class AWSModel {

	private String imagePath;
	String LabelDetect;
	
	@Autowired
	public AWSModel() {

	}


	public AWSModel(String imagePath, String LabelDetect) {
		this.imagePath = imagePath;
		this.LabelDetect = LabelDetect;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getLabelDetect() {
		return LabelDetect;
	}
	
}
