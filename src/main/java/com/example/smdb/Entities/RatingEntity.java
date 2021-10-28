package com.example.smdb.Entities;

import com.example.smdb.Entities.Embeding.RatingId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class RatingEntity {

    @EmbeddedId
    private RatingId ratingId;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn//(name = "id")
    private MovieEntity movieEntity;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn//=(name = "id")
    @JsonIgnore
    private UserEntity userEntity;

    private double rating;

    public RatingId getRatingId() {
        return ratingId;
    }

    public void setRatingId(RatingId ratingId) {
        this.ratingId = ratingId;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
