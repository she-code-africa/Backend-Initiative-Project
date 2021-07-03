package com.backendinitiative.services;

import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.exceptions.UserNotFoundException;
import com.backendinitiative.models.User;
import com.backendinitiative.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
     * */
    @Override
    public void updateUser(String userId, User updateUser) throws UserNotFoundException {
        Optional<User> foundUser = Optional.of(userDb.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist")));


        foundUser.ifPresent(user -> {
             if(updateUser.getFirstName() != null) user.setFirstName(updateUser.getFirstName());
             if(updateUser.getLastName() != null) user.setLastName(updateUser.getLastName());
             if(updateUser.getEmail() != null) user.setEmail(updateUser.getEmail());
             if(updateUser.getPassword() != null) user.setPassword(updateUser.getPassword());

             saveToDatabase(user);
        });
    }

    /**
     * Deletes a user with their id
     *
     * @author Amaka
     * @param userId
     * */
    public void deleteUser(String userId) throws UserNotFoundException{
        if(userDb.findByUserId(userId).isEmpty()) throw new UserNotFoundException("User does not exist");
        userDb.deleteById(userId);
    }
}
