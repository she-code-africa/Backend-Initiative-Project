package com.backendinitiative.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection="users")
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @DBRef
    private List<Rental> moviesRented;


    public void addMoviesRented(Rental rental){
        if(moviesRented == null){
            moviesRented = new ArrayList<>();

        }

        moviesRented.add(rental);
    }


}
