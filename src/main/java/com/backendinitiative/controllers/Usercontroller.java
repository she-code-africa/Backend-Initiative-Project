package com.backendinitiative.controllers;

import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.exceptions.UserNotFoundException;
import com.backendinitiative.models.User;
import com.backendinitiative.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class Usercontroller {

    @Autowired
    private UserService userService;

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseTransfer createNewUser(@RequestBody UserDto newUser){
        try{
            userService.createNewUser(newUser);
        }catch(EmailExistsException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        }
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseTransfer updateUser(@PathVariable String userId, @RequestBody User updatedUser){
        try{
            userService.updateUser(userId, updatedUser);
        }catch(UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        }
        return new ResponseTransfer("User updated successfully");
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseTransfer deleteUser(@PathVariable("userId") @RequestBody String userId){
        try{
            userService.deleteUser(userId);
        }catch(UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        }
        return new ResponseTransfer("User deleted successfully");
    }
}
