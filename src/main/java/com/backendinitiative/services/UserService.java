package com.backendinitiative.services;

import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.exceptions.UserNotFoundException;
import com.backendinitiative.models.User;

import java.util.List;

public interface UserService {
    User createNewUser(UserDto newUser) throws EmailExistsException;

    List<User> getAllUsers();

    User getOneUser(String userId) throws UserNotFoundException;
}
