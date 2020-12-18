package com.aijaycodes.movieApp.service;

import java.util.List;

import com.aijaycodes.movieApp.data.MovieData;
import com.aijaycodes.movieApp.data.RatingsData;
import com.aijaycodes.movieApp.data.RentalData;

import com.aijaycodes.movieApp.data.UserData;

public interface MovieService {
	
	 List<UserData> getUsers();
	 
	 List<RentalData> getRentals();
	 
	 List<RatingsData> getRatings();
	 
	 List<MovieData> getMovies();
	 
	 Object deleteRentals(int id);

	 Object deleteRating(int id);
	 
	 Object deleteUser(String id);
	 
	 Object deleteMovie(int id);
	 
	 Object updateUser(UserData data);
	 
	 Object updateRentals(RentalData data);
	 
	 Object updateRatings(RatingsData data);
	 
	 Object updateMovie(MovieData data);
	 
	 Object createRental(RentalData data);
	 
	 Object createRating(RatingsData data);
	 
	 Object createUser(UserData data);
	 
	 Object createMovie(MovieData data);

}
