package com.backendinitiative.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalDto {
    private LocalDate rentalDate;
    private String movieToRent;
}
