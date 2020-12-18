package com.aijaycodes.movieApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aijaycodes.movieApp.data.MovieData;
import com.aijaycodes.movieApp.data.RatingsData;
import com.aijaycodes.movieApp.data.RentalData;
import com.aijaycodes.movieApp.data.Response;
import com.aijaycodes.movieApp.data.UserData;

@Service
public class MovieServicesImpl implements MovieService {
	
	List<UserData> users = new ArrayList<>();
	List<MovieData> movies = new ArrayList<>();
	List<RatingsData> ratings = new ArrayList<>();
	List<RentalData> rentals = new ArrayList<>();
	
	Response response = new Response();
	
	public Object createMovie(MovieData data) {
		
		movies.add(data);
		response.setStatus("00");
		response.setMessage("Movie Added");
		return response;
	}
	
	public Object createUser(UserData data) {
		
		users.add(data);
		response.setStatus("00");
		response.setMessage("User created");
		return response;
	}
	
	public Object createRating(RatingsData data) {
		
		ratings.add(data);
		response.setStatus("00");
		response.setMessage("Rating Added");
		return response;
	}
	
	public Object createRental(RentalData data) {
		
		rentals.add(data);
		response.setStatus("00");
		response.setMessage("Rental Added");
		return response;
	}
	
	public Object updateMovie(MovieData data) {
		
		for(int i = 0; i < movies.size(); i++) {
			if (movies.get(i).getId() == data.getId()) {
				
				
				if(data.getCountry() == null || data.getCountry() == ""){
					System.out.println("country not modified");
				}else {
					movies.get(i).setCountry(data.getCountry());
				}
				if(data.getTitle()== null || data.getTitle()== ""){
					System.out.println("title not modified");
				}else {
					movies.get(i).setTitle(data.getTitle());
				}
				if(data.getYear() == null || data.getYear() == "") {
					System.out.println("year not modified");
				}else{
					movies.get(i).setYear(data.getYear());
				}
				
			}
			
		}
		response.setStatus("00");
		response.setMessage("Movie updated");
		return response;
	}
	
	public Object updateRatings(RatingsData data) {
	
		for(int i = 0; i < ratings.size(); i++) {
			if (ratings.get(i).getRatingsId() == data.getRatingsId()) {
				
				
				if(data.getMovieId() == null || data.getMovieId() == ""){
					System.out.println("movie not modified");
				} else{
					ratings.get(i).setMovieId(data.getMovieId());
				}
				if(data.getRating()== null || data.getRating()== ""){
					System.out.println("rating not modified");
				}else {
					ratings.get(i).setRating(data.getRating());
				}
				if(data.getUserId() == null || data.getUserId() == "") {
					System.out.println("user not modified");
				}else{
					ratings.get(i).setUserId(data.getUserId());
				}
				
			}
		}
		response.setStatus("00");
		response.setMessage("Rating updated");
		return response;
	}
	
	public Object updateRentals(RentalData data) {
		
		for(int i = 0; i < rentals.size(); i++) {
			if (rentals.get(i).getRentalId() == data.getRentalId()) {
				
				
				if(data.getMovieId() == null || data.getMovieId() == "" ) {
					System.out.println("movie not modified");
				}else{
					rentals.get(i).setMovieId(data.getMovieId());
				}
				if(data.getDate() == null || data.getDate() == "") {
					System.out.println("date not modified");
				}else{
					rentals.get(i).setDate(data.getDate());
				}
				if(data.getUserId() == null || data.getUserId() == ""){
					System.out.println("user not modified");
				}else {
					rentals.get(i).setUserId(data.getUserId());
				}
				
			}
		}
		response.setStatus("00");
		response.setMessage("Rental updated");
		return response;
	}
	
	public Object updateUser(UserData data) {
		
		for(int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserId() == data.getUserId()) {
				
				
				if(data.getEmail() == null || data.getEmail() == "") {
					System.out.println("email not modified");
				}
				else {
					users.get(i).setEmail(data.getEmail());
				}
				if(data.getName() == null || data.getName() == "") {
					System.out.println("name not modified");
				}else{
					users.get(i).setName(data.getName());
				}
				if(data.getPassword() == null || data.getPassword() == "") {
					System.out.println("password not modified");
				}else{
					users.get(i).setPassword(data.getPassword());
				}
				if(data.getUsername() == null || data.getUsername() == "") {
					System.out.println("username not modified");
				}else{
					users.get(i).setUsername(data.getUsername());
				}
				
			}
		}
		response.setStatus("00");
		response.setMessage("User updated");
		return response;
	}
	
	public Object deleteMovie(int id) {
		
		for(int i = 0; i < movies.size(); i++) {
			if (movies.get(i).getId() == id) {
				
				movies.remove(i);
			}
		}
		response.setStatus("00");
		response.setMessage("Movie Deleted");
		return response;
	}
	
	public Object deleteUser(String id) {
		
		for(int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserId() == id) {
				
				users.remove(i);
			}
		}
		response.setStatus("00");
		response.setMessage("User Deleted");
		return response;
	}
	
	public Object deleteRating(int id) {
		
		for(int i = 0; i < ratings.size(); i++) {
			if (ratings.get(i).getRatingsId() == id) {
				
				ratings.remove(i);
			}
		}
		response.setStatus("00");
		response.setMessage("Rating Deleted");
		return response;
	}
	public Object deleteRentals(int id) {
		
		for(int i = 0; i < rentals.size(); i++) {
			if (rentals.get(i).getRentalId() == id) {
				
				rentals.remove(i);
				
			}
		}
		response.setStatus("00");
		response.setMessage("Rental Deleted");
		return response;
	}
	
	public List<UserData> getUsers() {
		
		return users;
	}
	
	public List<RentalData> getRentals() {
		
		return rentals;
	}

	
	public List<RatingsData> getRatings() {
		
		return ratings;
	}
	
	public List<MovieData> getMovies() {
		
		return movies;
	}

}
