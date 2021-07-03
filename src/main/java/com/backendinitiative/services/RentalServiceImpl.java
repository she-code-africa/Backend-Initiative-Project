package com.backendinitiative.services;

import com.backendinitiative.dtos.RentalDto;
import com.backendinitiative.exceptions.MovieNotFoundException;
import com.backendinitiative.exceptions.RentalNotFoundException;
import com.backendinitiative.exceptions.UserNotFoundException;
import com.backendinitiative.models.Movie;
import com.backendinitiative.models.Rental;
import com.backendinitiative.models.User;
import com.backendinitiative.repository.MovieRepo;
import com.backendinitiative.repository.RentalRepo;
import com.backendinitiative.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RentalServiceImpl implements RentalService{
    @Autowired
    private RentalRepo rentalDb;

    @Autowired
    private MovieRepo movieDb;

    @Autowired
    private UserRepo userDb;

    @Override
    public List<Rental> getAllRentals() {
        return rentalDb.findAll();
    }

    @Override
    public Rental createRental(RentalDto rentMovie, String userId) throws MovieNotFoundException, UserNotFoundException {
        Optional<Movie> findMovieByName = Optional.of(movieDb.findMovieByMovieName(rentMovie.getMovieToRent())
                .orElseThrow(() -> new MovieNotFoundException("Movie does not exist")));

        Optional<User> findUser = Optional.of(userDb.findByUserId(userId))
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));

        Rental rental = new Rental();
        rental.setRentalDate(LocalDate.now());
        findMovieByName.ifPresent(rental::setRentedMovie);
        findUser.ifPresent(user -> user.addMoviesRented(rental));
        findUser.ifPresent(rental::setUserRenting);
        rentalDb.save(rental);
        userDb.save(rental.getUserRenting());

        return rental;
    }

    @Override
    public Rental viewOneRental(String rentalId) throws RentalNotFoundException{
        if (rentalDb.findByRentalId(rentalId).isEmpty()) throw new RentalNotFoundException("Rental doesn't seem to exist");
        return rentalDb.findByRentalId(rentalId).get();
    }

    @Override
    public void deleteRental(String rentalId) throws RentalNotFoundException{
        if (rentalDb.findByRentalId(rentalId).isEmpty()) throw new RentalNotFoundException("Rental doesn't seem to exist");
        rentalDb.deleteById(rentalId);
    }
}
