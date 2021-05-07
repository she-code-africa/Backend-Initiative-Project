package com.backendinitiative.models;

import com.backendinitiative.dtos.MovieDto;
import com.backendinitiative.exceptions.MovieExistsException;
import com.backendinitiative.repository.MovieRepo;
import com.backendinitiative.services.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MovieTest {
    @Autowired
    private MovieRepo movieDb;

    @Autowired
    private MovieService movieService;

    @Test
    void shouldCreateAnewMovie(){
        Movie newMovie = new Movie();
        newMovie.setMovieName("Jack and the beanstalk");
        newMovie.setYearOfRelease(1976);
        movieDb.save(newMovie);
        assertEquals(1, movieDb.count());
    }

    @Test
    void movieServiceAddsNewMovieToDatabase(){
        MovieDto newMovieDto = new MovieDto();
        newMovieDto.setMovieName("Titanic");
        newMovieDto.setYearOfRelease(1986);
        try{
            movieService.addMovie(newMovieDto);
        }catch(MovieExistsException e){
            System.out.println(e.getLocalizedMessage());
        }

        assertEquals(2, movieDb.count());
    }
}