package com.aijaycodes.movieApp.data;

public class MovieData {

	String id;
	
	String title;
	
	String yearOfProduction;
	
	String country;
	
	String producer;
	
	String genre;
	
	String length;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * public String getRatings() { return ratings; }
	 * 
	 * public void setRatings(String ratings) { this.ratings = ratings; }
	 */

	/*
	 * public String getYear() { return year; }
	 * 
	 * public void setYear(String year) { this.year = year; }
	 */
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(String yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
}
