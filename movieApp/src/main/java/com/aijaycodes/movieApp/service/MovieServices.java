package com.aijaycodes.movieApp.service;

import java.util.ArrayList;
import java.util.List;

import com.aijaycodes.movieApp.data.MovieData;

public class MovieServices {
	
	public void createMovie(MovieData data) {
		
		List<MovieData> movies = new ArrayList<>();
		movies.add(data);
	}
	
	public void updateMovie(int id) {
		List<MovieData> movies = new ArrayList<>();
		for(int i = 0; i <= movies.size(); i++) {
			if (movies.get(i).getId() == id) {
				
			}
		}
	}
	
	public void deleteMovie(int id) {
		
	}
	
	public void getMovies() {
		
	}

}
