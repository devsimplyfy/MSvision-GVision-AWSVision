package com.example;

public class MSVision {

	
	private String msVisionString;
	private String imageToAnalyze;

	public MSVision() {

	}

	public MSVision(String imageToAnalyze, String msVisionString) {

		this.msVisionString = msVisionString;
		this.imageToAnalyze = imageToAnalyze;
	}


	public String getImageToAnalyze() {
		return imageToAnalyze;
	}

	public String getMsVisionString() {
		return msVisionString;
	}

}
