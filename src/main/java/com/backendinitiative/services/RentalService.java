package com.backendinitiative.services;

import com.backendinitiative.dtos.RentalDto;
import com.backendinitiative.exceptions.MovieNotFoundException;
import com.backendinitiative.exceptions.RentalNotFoundException;
import com.backendinitiative.exceptions.UserNotFoundException;
import com.backendinitiative.models.Rental;

import java.util.List;


public interface RentalService {
    List<Rental> getAllRentals();

    Rental createRental(RentalDto rentMovie, String userId) throws MovieNotFoundException, UserNotFoundException;

    Rental viewOneRental(String rentalId) throws RentalNotFoundException;

    void deleteRental(String rentalId) throws RentalNotFoundException;
}
