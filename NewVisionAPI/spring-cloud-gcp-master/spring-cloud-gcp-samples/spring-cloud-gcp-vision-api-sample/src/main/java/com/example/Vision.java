package com.example;

import org.json.simple.JSONArray;

public class Vision {

	private JSONArray gVisionArray;
	private String imageUrl;

	public Vision() {

	}

	public Vision(JSONArray gVisionArray, String imageUrl) {
		this.gVisionArray = gVisionArray;
		this.imageUrl = imageUrl;
	}

	public JSONArray getgVisionArray() {
		return gVisionArray;
	}

	public String getImageUrl() {
		return imageUrl;
	}

}
