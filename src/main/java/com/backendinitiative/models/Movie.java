package com.backendinitiative.models;

import lombok.Data;

@Data
public class Movie {
    private static int movieIdCount = 1;
    private int movieId;
    private String movieName;
    private int yearOfRelease;

    public Movie(String movieName, int yearOfRelease) {
        this.movieId = movieIdCount++;
        this.movieName = movieName;
        this.yearOfRelease = yearOfRelease;
    }
}

