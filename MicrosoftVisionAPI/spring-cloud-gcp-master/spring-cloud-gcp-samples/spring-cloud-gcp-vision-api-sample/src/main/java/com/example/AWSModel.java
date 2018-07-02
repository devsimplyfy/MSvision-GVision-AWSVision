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
	// @SuppressWarnings("rawtypes")
	// private List<Label> LabelDetection;

	@Autowired
	public AWSModel() {

	}

	@SuppressWarnings("rawtypes")
	// public AWSModel(String photo, List<Label> LabelDetection) {
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

	public void setLabelDetect(String LabelDetect) {
		LabelDetect = LabelDetect;
	}

	// public List<Label> getLabelDetection() {
	// return LabelDetection;
	// }
	//
	// public void setLabelDetection(List<Label> labelDetection) {
	// LabelDetection = labelDetection;
	// }

}
