package com.backendinitiative.models;

import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.repository.UserRepo;
import com.backendinitiative.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {
    @Autowired
    private UserRepo userDb;

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUser(){
        User firstUser = new User();
        firstUser.setUsername("babe222");
        firstUser.setFirstName("Amaka");
        firstUser.setLastName("Mbah");
        firstUser.setEmail("amy@gmail.com");
        firstUser.setPassword("babe2211");

        assertEquals("Amaka", firstUser.getFirstName());
    }

    @Test
    void shouldSaveNewUserToDatabase(){
        User secondUser = new User();
        secondUser.setUsername("babe222");
        secondUser.setFirstName("Amaka");
        secondUser.setLastName("Mbah");
        secondUser.setEmail("amy@gmail.com");
        secondUser.setPassword("babe2211");

        userDb.save(secondUser);
        assertEquals(1, userDb.count());
    }

    @Test
    void shouldThrowAnErrorIfUserAlreadyExists(){
        UserDto secondUser = new UserDto();
        secondUser.setUsername("babe222");
        secondUser.setFirstName("Amaka");
        secondUser.setLastName("Mbah");
        secondUser.setEmail("amy@gmail.com");
        secondUser.setPassword("babe2211");

        try{
            userService.createNewUser(secondUser);
        }catch(EmailExistsException e){
            e.getLocalizedMessage();
        }
        assertEquals(1, userDb.count());
    }
}