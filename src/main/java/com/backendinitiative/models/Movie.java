package com.backendinitiative.models;

import lombok.Data;

@Data
public class Movie {
    private Integer movieId;
    private String movieName;
    private int yearOfRelease;

    public Movie(Integer movieId, String movieName, int yearOfRelease) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.yearOfRelease = yearOfRelease;
    }
}

