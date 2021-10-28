package com.example.smdb.Respiratories;

import com.example.smdb.Entities.GenreEntity;
import com.example.smdb.Entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

    @Query("select genre from GenreEntity genre, MovieEntity movie where movie.movie_ids = ?1")
    List<GenreEntity> findAllByMovieId(int movieId);

    @Query("select genre from GenreEntity genre where genre.id = ?1")
    List<GenreEntity> findAllById(int id);
}
