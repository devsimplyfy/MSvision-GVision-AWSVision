package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MSVision {

	private long id;
	// private String content;
	private JSONObject json;
	private JSONArray jsonarray;
	private String jsonString;
	private String imageToAnalyze;

	public MSVision() {

	}

	// public MSVision(long id, JSONObject json, String jsonString) {
	public MSVision(long id, String imageToAnalyze, String jsonString) {
		this.id = id;
		// this.jsonarray = jsonarray;
		this.jsonString = jsonString;
		// this.json = json;
		this.imageToAnalyze = imageToAnalyze;
	}

	public long getId() {
		return id;
	}

	// public JSONArray getJsonarray() {
	// return jsonarray;
	// }

	// public String getImageUrl() {
	// return imageUrl;
	// }

	// public JSONObject getJson() {
	// return json;
	// }

	public String getJsonString() {
		return jsonString;
	}

	public String getImageToAnalyze() {
		return imageToAnalyze;
	}

	// public void setJson(JSONObject json) {
	// this.json = json;
	// }

	/*
	 * public String getContent() { return content; }
	 */
}
