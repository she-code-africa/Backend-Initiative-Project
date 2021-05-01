package com.backendinitiative.models;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;

    public User(Integer userId,String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
