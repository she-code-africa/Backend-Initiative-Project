package com.backendinitiative.controllers;

import com.backendinitiative.dtos.RentalDto;
import com.backendinitiative.exceptions.MovieNotFoundException;
import com.backendinitiative.exceptions.RentalNotFoundException;
import com.backendinitiative.exceptions.UserNotFoundException;
import com.backendinitiative.models.Rental;
import com.backendinitiative.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class Rentalcontroller {
    @Autowired
    private RentalService rentalService;

    @PostMapping("{userId}/rental/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseTransfer createRental(@RequestBody RentalDto newRental, @PathVariable String userId){
        try{
            rentalService.createRental(newRental, userId);
        }catch(MovieNotFoundException | UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getLocalizedMessage());
        }
        return new ResponseTransfer("Rental created successfully");
    }

    @GetMapping("rental/all")
    @ResponseBody
    public List<Rental> getRentals(){
        return rentalService.getAllRentals();
    }

    @GetMapping("rental/{rentalId}")
    @ResponseBody
    public ResponseEntity<?> getOneRental(@PathVariable String rentalId){
        Rental foundRental;
        try{
            foundRental = rentalService.viewOneRental(rentalId);
        }catch(RentalNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        }
        return new ResponseEntity<>(foundRental, HttpStatus.OK);
    }


    @DeleteMapping("rental/{rentalId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseTransfer deleteRental(@PathVariable String rentalId){
        try{
            rentalService.deleteRental(rentalId);
        }catch(RentalNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        }
        return new ResponseTransfer("Rental deleted successfully");
    }
}
