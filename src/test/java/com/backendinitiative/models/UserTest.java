package com.backendinitiative.models;

import com.backendinitiative.dtos.RentalDto;
import com.backendinitiative.dtos.UserDto;
import com.backendinitiative.exceptions.EmailExistsException;
import com.backendinitiative.exceptions.MovieNotFoundException;
import com.backendinitiative.exceptions.UserNotFoundException;
import com.backendinitiative.repository.UserRepo;
import com.backendinitiative.services.RentalService;
import com.backendinitiative.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class UserTest {
    @Autowired
    private UserRepo userDb;

    @Autowired
    private UserService userService;

    @Autowired
    private RentalService rentalService;

    @Test
    void shouldCreateUser(){
        User firstUser = new User();
        firstUser.setFirstName("Amaka");
        firstUser.setLastName("Mbah");
        firstUser.setEmail("amy@gmail.com");
        firstUser.setPassword("babe2211");

        assertEquals("Amaka", firstUser.getFirstName());
    }

    @Test
    void shouldSaveNewUserToDatabase(){
        User secondUser = new User();
        secondUser.setFirstName("Amaka");
        secondUser.setLastName("Mbah");
        secondUser.setEmail("amy@gmail.com");
        secondUser.setPassword("babe2211");

        userDb.save(secondUser);

        assertEquals(3, userDb.count());
    }

    @Test
    void shouldThrowAnErrorIfUserAlreadyExists(){
        UserDto secondUser = new UserDto();
        secondUser.setFirstName("Amaka");
        secondUser.setLastName("Mbah");
        secondUser.setEmail("amy@gmail.com");
        secondUser.setPassword("babe2211");

        UserDto thirdUser = new UserDto();
        thirdUser.setFirstName("Fina");
        thirdUser.setLastName("Jonah");
        thirdUser.setEmail("finny@gmail.com");
        thirdUser.setPassword("finn234");

        try{
//            userService.createNewUser(secondUser);
            userService.createNewUser(thirdUser);
        }catch(EmailExistsException e){
            e.getLocalizedMessage();
        }
        assertEquals(2, userDb.count());
    }

    @Test
    void shouldReturnAUserWithRentalListSize(){
        RentalDto newRental = new RentalDto();
        newRental.setRentalDate(LocalDate.now());
        newRental.setMovieToRent("titanic");

        try{
            Rental rental = rentalService.createRental(newRental, "609402ecebd6013a44e58809");
//            log.info("Rental created --> {}", rental);


            User user = userService.getOneUser("609402ecebd6013a44e58809");
            assertThat(user).isNotNull();
//            log.info("User info --> {}", user);

            int rentalSize = user.getMoviesRented().size();
            assertThat(rentalSize).isGreaterThan(0);
        }catch(UserNotFoundException | MovieNotFoundException e){
            e.printStackTrace();
        }
    }
}