package com.example.smdb.Services;

import com.example.smdb.Entities.Embeding.RatingId;
import com.example.smdb.Entities.MovieEntity;
import com.example.smdb.Entities.RatingEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Respiratories.MovieRepository;
import com.example.smdb.Respiratories.RatingRepository;
import com.example.smdb.Respiratories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<RatingEntity> getRatings(){
        return ratingRepository.findAll();
    }

    public RatingEntity userRate(int userId, int movieId, double rating) {
        UserEntity user = userRepository.getById(userId);
        MovieEntity movie = movieRepository.getById(movieId);
        RatingId ratingId = new RatingId(userId, movieId);

        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setRatingId(ratingId);
        ratingEntity.setMovieEntity(movie);
        ratingEntity.setUserEntity(user);
        ratingEntity.setRating(rating);

        user.getRatings().add(ratingEntity);
        movie.getRatings().add(ratingEntity);

        double previousAverageRating = movie.getVoteAverage();
        double modifiedAverageRating = (previousAverageRating + rating) / 2;
        movie.setVoteAverage(modifiedAverageRating);
        movieRepository.save(movie);

        return ratingRepository.save(ratingEntity);
    }

}
