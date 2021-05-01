package com.backendinitiative.controllers;

import com.backendinitiative.models.Rental;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Rentalcontroller {
    private final Moviecontroller movies = new Moviecontroller();
    private final Usercontroller users = new Usercontroller();

    private final List<Rental> rentals = Arrays.asList(
            new Rental(1,LocalDate.of(2020,5,20), movies.getMovies().get(0), users.getUsers().get(1)),
            new Rental(2,LocalDate.of(2005, 6, 12), movies.getMovies().get(2), users.getUsers().get(2))
    );

    @GetMapping("/rentals")
    public List<Rental> getRentals(){
        return rentals;
    }
}
