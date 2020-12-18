package com.aijaycodes.movieApp.controller;


import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aijaycodes.movieApp.data.MovieData;
import com.aijaycodes.movieApp.data.RatingsData;
import com.aijaycodes.movieApp.data.RentalData;
import com.aijaycodes.movieApp.data.Response;
import com.aijaycodes.movieApp.data.UserData;
import com.aijaycodes.movieApp.service.MovieService;

@RestController
@RequestMapping("/movieApp")
public class MovieController {
	@Autowired
	MovieService service;
	

	 
	@GetMapping("/user")
	 public List<UserData> getUsers() {
		 
		 List<UserData> responseData = new ArrayList<>();
		 responseData = service.getUsers();
		 
		 return responseData;
	 }
	
	@GetMapping("/rental")
	 public List<RentalData> getRentals(){
		 List<RentalData> responseData = new ArrayList<>();
		 responseData = service.getRentals();
		 
		 return responseData;
	 }
	 
	@GetMapping("/rating")
	 public List<RatingsData> getRatings(){
		 List<RatingsData> responseData = new ArrayList<>();
		 responseData = service.getRatings();
		 
		 return responseData;
	 }
	 
	@GetMapping("/movie")
	 public List<MovieData> getMovies(){
		 List<MovieData> responseData = new ArrayList<>();
		 responseData = service.getMovies();
		 
		 return responseData;
	 }
	
	@PostMapping("/deleteRental/{rentalId}")
	public Object deleteRental(@PathVariable("rentalId") String id) {
		
		return service.deleteRentals(id);
		
	}
	
	@PostMapping("/deleteRating/{ratingId}")
	public Object deleteRating(@PathVariable("ratingId") String id) {
		
		return service.deleteRating(id);
		
	}
	
	
	@PostMapping("/deleteMovie/{movieId}")
	public Object deleteMovie(@PathVariable("movieId") String id) {
		
		return service.deleteMovie(id);
		
	}
	
	@PostMapping("/deleteUser/{userId}")
	public Object deleteUser(@PathVariable("userId") String userId) {
		
		return service.deleteUser(userId);
		
	}
	 
	@PostMapping("/updateUser")
	public Object updateUser(@RequestBody UserData data) {
		return service.updateUser(data);
	}
	
	@PostMapping("/updateRental")
	public Object updateRental(@RequestBody RentalData data) {
		return service.updateRentals(data);
	}
	
	@PostMapping("/updateRating")
	public Object updateRating(@RequestBody RatingsData data) {
		return service.updateRatings(data);
	}
	
	@PostMapping("/updateMovie")
	public Object updateMovie(@RequestBody MovieData data) {
		return service.updateMovie(data);
	}
	
	@PostMapping("/createUser")
	public Object createUser(@RequestBody UserData data ) {
		
		return service.createUser(data);
	}
	
	@PostMapping("/createMovie")
	public Object createMovie(@RequestBody MovieData data ) {
		
		return service.createMovie(data);
	}
	
	@PostMapping("/createRating")
	public Object createRating(@RequestBody RatingsData data ) {
		
		return service.createRating(data);
	}

	@PostMapping("/createRental")
	public Object createRental(@RequestBody RentalData data ) {
	
	return service.createRental(data);
}
	 
}
