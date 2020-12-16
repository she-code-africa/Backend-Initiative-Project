package com.aijaycodes.movieApp.data;

public class RatingsData {

	int ratingsId;
	
	String movieId;
	
	String userId;
	
	String rating;

	public int getRatingsId() {
		return ratingsId;
	}

	public void setRatingsId(int ratingsId) {
		this.ratingsId = ratingsId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
