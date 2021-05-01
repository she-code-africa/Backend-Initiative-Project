package com.backendinitiative.controllers;

import com.backendinitiative.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Moviecontroller {
    private final List<Movie> movies = Arrays.asList(
            new Movie(1,"Man in the Iron mask", 1998),
            new Movie(2,"Titanic", 1997),
            new Movie(3,"Alladin", 1992)
    );

    @GetMapping("/movies")
    public List<Movie> getMovies(){
        return movies;
    }
}
