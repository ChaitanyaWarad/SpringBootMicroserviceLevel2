package com.javatpoint.model;

public class MovieSummary {

	private String Title;
	private String Overview;

	public MovieSummary() {
	}

	public MovieSummary(String title, String overview) {
		Title = title;
		Overview = overview;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getOverview() {
		return Overview;
	}

	public void setOverview(String overview) {
		Overview = overview;
	}

}
