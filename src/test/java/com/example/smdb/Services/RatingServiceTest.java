package com.example.smdb.Services;

import com.example.smdb.Entities.Embeding.RatingId;
import com.example.smdb.Entities.MovieEntity;
import com.example.smdb.Entities.RatingEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Respiratories.MovieRepository;
import com.example.smdb.Respiratories.RatingRepository;
import com.example.smdb.Respiratories.RoleRepository;
import com.example.smdb.Respiratories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RatingServiceTest {

    private RatingRepository ratingRepository = Mockito.mock(RatingRepository.class);
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    private RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    private RatingService ratingService = Mockito.mock(RatingService.class);

    @Test
    void getRatings_AllRatingsFetched_True() {
        RatingEntity saraRating = new RatingEntity();
        RatingEntity alyRating = new RatingEntity();

        List<RatingEntity> ratings = new ArrayList(Arrays.asList(alyRating, saraRating));

        Mockito.when(ratingService.getRatings()).thenReturn(ratings);
        assertEquals(2, ratingService.getRatings().size());
    }

    @Test
    void userRate_RatingAdded_True() {
        UserEntity user = new UserEntity();
        user.setId(1);
        MovieEntity movie = new MovieEntity();
        movie.setId(5);

        RatingEntity rate = new RatingEntity();
        rate.setRating(9.9);
        Mockito.when(ratingService.userRate(user.getId(), movie.getId(), 9.9)).thenReturn(rate);

        assertEquals(rate, ratingService.userRate(user.getId(), movie.getId(), 9.9));

    }
}