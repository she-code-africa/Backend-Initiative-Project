package com.backendinitiative.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection="rentals")
public class Rental {
    @Id
    private String rentalId;
    private LocalDate rentalDate;

    @DBRef
    private Movie rentedMovie;

    @ToString.Exclude
    @DBRef
    @JsonIgnore
    private User userRenting;
}
