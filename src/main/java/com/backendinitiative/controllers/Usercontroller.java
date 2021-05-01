package com.backendinitiative.controllers;

import com.backendinitiative.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Usercontroller {
    private final List<User> users = Arrays.asList(
            new User(1,"Amaka", "Mbah", "ami@gmail.com"),
            new User(2,"Senita", "Blue", "seni@email.com"),
            new User(3,"Jane", "Seyfried", "ane@yahoo.com"),
            new User(4,"John", "Momoh", "jo@john.com")
    );

    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }
}
