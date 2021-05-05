package com.backendinitiative.controllers;

import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class Usercontroller {

    @Autowired
    private UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<?> createNewUser(@RequestBody UserDto newUser){
        try{
            userService.createNewUser(newUser);
        }catch(EmailExistsException emailExists){
            return new ResponseEntity<>(emailExists.getLocalizedMessage(), HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
    }

    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
