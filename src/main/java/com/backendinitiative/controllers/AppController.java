package com.backendinitiative.controllers;

import com.backendinitiative.models.Movie;
import com.backendinitiative.models.Rental;
import com.backendinitiative.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class AppController {

    private final List<User> users = Arrays.asList(
            new User("Amaka", "Mbah", "ami@gmail.com"),
            new User("Senita", "Blue", "seni@email.com"),
            new User("Jane", "Seyfried", "ane@yahoo.com"),
            new User("John", "Momoh", "jo@john.com")
    );

    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }

    private final List<Movie> movies = Arrays.asList(
            new Movie("Man in the Iron mask", 1998),
            new Movie("Titanic", 1997),
            new Movie("Alladin", 1992)
    );

    @GetMapping("/movies")
    public List<Movie> getMovies(){
        return movies;
    }

    private final List<Rental> rentals = Arrays.asList(
        new Rental(LocalDate.of(2020,5,20), movies.get(0), users.get(1)),
        new Rental(LocalDate.of(2005, 6, 12), movies.get(2), users.get(2))
    );

    @GetMapping("/rentals")
    public List<Rental> getRentals(){
        return rentals;
    }
}
