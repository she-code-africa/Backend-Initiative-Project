package com.backendinitiative.services;

import com.backendinitiative.dtos.MovieDto;
import com.backendinitiative.exceptions.MovieExistsException;
import com.backendinitiative.exceptions.MovieNotFoundException;
import com.backendinitiative.models.Movie;
import com.backendinitiative.repository.MovieRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepo movieDb;
    /**
     * Create an instance of movie model
     *
     * Collect new movie details (MovieDto - frontend) and set it to movie model
     *
     * To avoid duplicate movies, check database if movie exists using movie name,
     * and throw an MovieExistsException if movie exists
     *
     * @author Amaka
     * @param newMovieDto
     * @return modelMovie
     * */
    @Override
    public Movie addMovie(MovieDto newMovieDto) throws MovieExistsException {
        Movie newMovie = new Movie();

        if(movieDb.findMovieByMovieName(newMovieDto.getMovieName().toLowerCase()).isPresent()) throw new MovieExistsException("Movie already exists");

        newMovie.setMovieName(newMovieDto.getMovieName().toLowerCase());
        newMovie.setYearOfRelease(newMovieDto.getYearOfRelease());
        saveMovieToDatabase(newMovie);
        return newMovie;
    }

    private void saveMovieToDatabase(Movie movie){
        movieDb.save(movie);
    }

    /**
     * Gets all movies in the database
     *
     * @author Amaka
     * @return all movies in the database
     * */
    @Override
    public List<Movie> viewAllMovies() {
        return movieDb.findAll();
    }

    /**
     * Get a movie by id
     *
     * @author Amaka
     * @param movieId
     * @return found movie
     * */
    @Override
    public Movie getOneMovie(String movieId) throws MovieNotFoundException {
        if(movieDb.findByMovieId(movieId).isEmpty()) throw new MovieNotFoundException("Movie does not exist");
        return movieDb.findByMovieId(movieId).get();
    }

    /**
     * Updates movie with their id
     * and save in the database
     *
     * @author Amaka
     * @param movieId
     * */
    @Override
    public void updateMovie(String movieId, Movie updateMovie) throws MovieNotFoundException {
        Optional<Movie> foundMovie = Optional.of(movieDb.findByMovieId(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie does not exist")));

        foundMovie.ifPresent(movie -> {
            if (updateMovie.getMovieName() != null) movie.setMovieName(updateMovie.getMovieName());
            if (updateMovie.getYearOfRelease() != 0) movie.setYearOfRelease(updateMovie.getYearOfRelease());

            saveMovieToDatabase(movie);
        });
    }

    /**
     * Deletes a movie with their id
     *
     * @author Amaka
     * @param movieId
     * */
    @Override
    public void deleteMovie(String movieId) throws MovieNotFoundException {
        if(movieDb.findByMovieId(movieId).isEmpty()) throw new MovieNotFoundException("Movie does not exist");
        movieDb.deleteById(movieId);
    }
}

