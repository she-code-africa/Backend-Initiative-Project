package com.backendinitiative.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="movies")
public class Movie {
    @Id
    private String movieId;
    private String movieName;
    private int yearOfRelease;

}

