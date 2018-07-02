package com.example;

import org.json.simple.JSONArray;

public class Vision {

	private long id;
	// private String content;
	private JSONArray jsonarray;
	private String imageUrl;

	public Vision() {

	}

	public Vision(long id, JSONArray jsonarray, String imageUrl) {
		this.id = id;
		this.jsonarray = jsonarray;
		this.imageUrl = imageUrl;
	}

	public long getId() {
		return id;
	}

	public JSONArray getJsonarray() {
		return jsonarray;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	/*
	 * public String getContent() { return content; }
	 */
}
