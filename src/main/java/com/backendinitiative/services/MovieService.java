package com.backendinitiative.services;

import com.backendinitiative.dtos.MovieDto;
import com.backendinitiative.exceptions.MovieExistsException;
import com.backendinitiative.exceptions.MovieNotFoundException;
import com.backendinitiative.models.Movie;

import java.util.List;

public interface MovieService {
    Movie addMovie(MovieDto newMovie) throws MovieExistsException;

    List<Movie> viewAllMovies();

    Movie getOneMovie(String movieId) throws MovieNotFoundException;

    void updateMovie(String movieId, Movie updateMovie) throws MovieNotFoundException;

    void deleteMovie(String movieId) throws MovieNotFoundException;

}
