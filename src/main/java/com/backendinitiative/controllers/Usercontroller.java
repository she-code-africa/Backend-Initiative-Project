package com.backendinitiative.controllers;

import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.exceptions.UserNotFoundException;
import com.backendinitiative.models.User;
import com.backendinitiative.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Usercontroller {

    @Autowired
    private UserService userService;

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseTransfer createNewUser(@RequestBody UserDto newUser){
        try{
            userService.createNewUser(newUser);
        }catch(EmailExistsException emailExists){
            return new ResponseTransfer(emailExists.getLocalizedMessage());
        }
        return new ResponseTransfer("Registration successful");
    }

    @GetMapping("/users/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getOneUser(@PathVariable("userId") @RequestBody String userId){
        User foundUser;
        try{
            foundUser = userService.getOneUser(userId);
        }catch(UserNotFoundException ex){
            return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") @RequestBody String userId, UserDto updatedUser){
        User foundUser;
        try{
            foundUser = userService.updateUser(userId, updatedUser);
        }catch(UserNotFoundException ex){
            return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }
}
