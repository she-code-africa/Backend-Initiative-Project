package com.backendinitiative.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserTest {

    User firstUser, secondUser;

    @BeforeEach
    void setUp() {
        firstUser = new User(1,"Amaka", "Mbah", "ami@gmail.com");
        secondUser = new User(2,"Janet", "Nkwocha", "jani@gmail.com");
    }

    @AfterEach
    void tearDown() {
        firstUser = null;
        secondUser = null;
    }

    @Test
    void shouldCreateUser(){
        firstUser.setFirstName("Amaka");
        firstUser.setLastName("Mbah");
        firstUser.setEmail("ami@gmail.com");

        assertEquals("Amaka", firstUser.getFirstName());
    }
}