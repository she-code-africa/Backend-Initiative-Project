package com.backendinitiative.services;

import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.models.User;
import com.backendinitiative.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userDb;

    /**
    * Create an instance of user model
    *
    * Collect new user details (UserDto - frontend) and set it to user model
    *
    * To avoid duplicate users, check database if user exists using email,
    * and throw an EmailExistsException if user exists
    *
    * @author Amaka
     * @param newUser
     * @return modelUser
    * */
    @Override
    public User createNewUser(UserDto newUser) throws EmailExistsException {
        User modelUser = new User();

        if(userDb.findUserByEmail(newUser.getEmail()).isPresent()) throw new EmailExistsException("User already exists");

        modelUser.setUsername(newUser.getUsername());
        modelUser.setFirstName(newUser.getFirstName());
        modelUser.setLastName(newUser.getLastName());
        modelUser.setEmail(newUser.getEmail());
        modelUser.setPassword(newUser.getPassword());
        addUserToDatabase(modelUser);
        return modelUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userDb.findAll();
    }

    private void addUserToDatabase(User user){
        userDb.save(user);
    }
}
