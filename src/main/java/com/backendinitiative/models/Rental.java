package com.backendinitiative.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Rental {
    private static int rentalCountId = 1;
    private Integer rentalId;
    private LocalDate rentalDate;
    private Movie rentedMovie;
    private User userRenting;

    public Rental(LocalDate rentalDate, Movie rentedMovie, User userRenting) {
        this.rentalId = rentalCountId++;
        this.rentalDate = rentalDate;
        this.rentedMovie = rentedMovie;
        this.userRenting = userRenting;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", rentalDate=" + rentalDate +
                ", rentedMovie=" + rentedMovie +
                ", userRenting=" + userRenting +
                '}';
    }
}
