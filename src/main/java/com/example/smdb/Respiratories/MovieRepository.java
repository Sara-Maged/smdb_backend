package com.example.smdb.Respiratories;

import com.example.smdb.Entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    MovieEntity findMovieEntityByTitle(String title);
    MovieEntity getById(int id);

    @Query("select m from MovieEntity m where m.flags > 0")
    List<MovieEntity> findAllByFlagsGreaterThan();

    List<MovieEntity> findAllByAdultIsFalseOrderByVoteAverageDesc();

    @Query("select movie from MovieEntity movie where movie.movie_ids = ?1")
    Collection<MovieEntity> findAllByGenre(int id);
}
