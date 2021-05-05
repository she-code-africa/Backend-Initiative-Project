package com.backendinitiative.services;

import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.exceptions.UserNotFoundException;
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

        modelUser.setFirstName(newUser.getFirstName());
        modelUser.setLastName(newUser.getLastName());
        modelUser.setEmail(newUser.getEmail());
        modelUser.setPassword(newUser.getPassword());
        saveToDatabase(modelUser);
        return modelUser;
    }

    private void saveToDatabase(User user){
        userDb.save(user);
    }

    /**
     * Gets all users in the database
     *
     * @author Amaka
     * @return all users in the database
     * */

    @Override
    public List<User> getAllUsers() {
        return userDb.findAll();
    }

    /**
     * Get a user by id
     *
     * @author Amaka
     * @param userId
     * @return found user
     * */
    @Override
    public User getOneUser(String userId) throws UserNotFoundException {
        if(userDb.findByUserId(userId).isEmpty()) throw new UserNotFoundException("User not found");
        return userDb.findByUserId(userId).get();
    }

    /**
     * Updates user with their id
     * and save in the database
     *
     * @author Amaka
     * @param userId
     * @return user updated
     * */
    @Override
    public User updateUser(String userId, UserDto updateUser) throws UserNotFoundException {
        return userDb.findByUserId(userId).map(user -> {
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setEmail(updateUser.getEmail());
            user.setPassword(updateUser.getPassword());
            saveToDatabase(user);
            return user;
        }).orElseThrow(() -> new UserNotFoundException("User does not exist"));
    }

}
