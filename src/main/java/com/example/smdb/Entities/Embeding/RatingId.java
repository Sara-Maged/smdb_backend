package com.example.smdb.Entities.Embeding;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable {

    private int movieId;
    private int userId;

    public RatingId(int movieId, int userId) {
        this.movieId = movieId;
        this.userId = userId;
    }

    public RatingId() {

    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingId)) return false;
        RatingId rating = (RatingId) o;
        return getMovieId() == rating.getMovieId() && getUserId() == rating.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getUserId());
    }
}
