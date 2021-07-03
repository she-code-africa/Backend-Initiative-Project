package com.backendinitiative.controllers;

import com.backendinitiative.dtos.MovieDto;
import com.backendinitiative.exceptions.*;
import com.backendinitiative.models.Movie;
import com.backendinitiative.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/movie/")
public class Moviecontroller {
    @Autowired
    private MovieService movieService;

    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseTransfer createNewMovie(@RequestBody MovieDto newMovie){
        try{
            movieService.addMovie(newMovie);
        }catch(MovieExistsException ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getLocalizedMessage());
        }
        return new ResponseTransfer("Movie created successfully");
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Movie> getAllMovies(){
        return movieService.viewAllMovies();
    }

    @GetMapping("{movieId}")
    public ResponseEntity<?> viewMovie(@PathVariable("movieId") @RequestBody String movieId){
        Movie foundMovie;
        try{
            foundMovie = movieService.getOneMovie(movieId);
        }catch(MovieNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        }
        return new ResponseEntity<>(foundMovie, HttpStatus.OK);
    }

    @PutMapping("{movieId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseTransfer updateMovie(@PathVariable("movieId") @RequestBody String movieId, Movie updateMovie){
        try{
            movieService.updateMovie(movieId, updateMovie);
        }catch(MovieNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        }
        return new ResponseTransfer("Movie updated successfully");
    }

    @DeleteMapping("{movieId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseTransfer deleteMovie(@PathVariable("movieId") @RequestBody String movieId){
        try{
            movieService.deleteMovie(movieId);
        }catch(MovieNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        }
        return new ResponseTransfer("Movie deleted successfully");
    }
}
