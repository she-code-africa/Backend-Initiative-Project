package com.backendinitiative.repository;

import com.backendinitiative.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepo extends MongoRepository<Movie, String> {
    Optional<Movie> findMovieByMovieName(String movieName);

    Optional<Movie> findByMovieId(String movieId);
}
