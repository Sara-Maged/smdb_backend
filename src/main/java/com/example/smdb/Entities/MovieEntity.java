package com.example.smdb.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@Entity
@Table(name = "movie_entity")
//@NamedQuery(name = "sortByVoteAverage", query = "select m from MovieEntity m order by m.vote_average ,DESC")
//@NamedQuery(name="averageRating", query = "select avg(e.rating) from RatingEntity e where e.ratingId.movieId = ?1")
public class MovieEntity {
    @Id
    private int id;

    private String original_language;
    private String original_title;
    @Column(columnDefinition =  "varchar(800)")
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean adult;
    private String backdrop_path;
    private boolean video;
    @Column(name = "vote_average")
    private double voteAverage;
    private int vote_count;

    private int flags = 0;

    @OneToMany(mappedBy = "movieEntity", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Collection<RatingEntity> ratings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn//(name = "genres")
    @JsonIgnore
    private Collection<GenreEntity> movie_ids = new ArrayList<>();

    @ManyToOne//(mappedBy = "userEntity", cascade = CascadeType.MERGE)
    private UserEntity user;

    public MovieEntity() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public Collection<GenreEntity> getGenre_ids() {
        return movie_ids;
    }

    public void setGenre_ids(Collection<GenreEntity> genre_ids) {
        this.movie_ids = genre_ids;
    }

    public Collection<RatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(Collection<RatingEntity> ratings) {
        this.ratings = ratings;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }
}
