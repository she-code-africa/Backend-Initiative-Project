package com.backendinitiative.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection="rentals")
public class Rental {
    @Id
    private String rentalId;
    private LocalDate rentalDate;
    private Movie rentedMovie;
    private User userRenting;

}
