package com.backendinitiative.repository;

import com.backendinitiative.models.Rental;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepo extends MongoRepository<Rental, String> {

    Optional<Rental> findByRentalId(String rentalId);
}
