package com.example.smdb.Respiratories;

import com.example.smdb.Entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {
<<<<<<< HEAD
//    List<RatingEntity> findAllByMovieEntity_IdWithin(int id);

//    List<RatingEntity> findAllByRatingIdUserIdWithin();
//    List<RatingEntity> getAllByRatingIdUserIdWithin(int id);

//    @Query("select r from RatingEntity r where r.ratingId.userId = id order by r.rating")
//    List<RatingEntity> findByUserEntityIdWithinOrderByRating(Integer id);
=======
<<<<<<< HEAD
>>>>>>> 390340688dd86b7b11973b966b6888c58450101a

    @Query("select ratnigEntity from RatingEntity ratnigEntity where ratnigEntity.ratingId.userId = ?1 and ratnigEntity.rating = (select max (r.rating) from ratnigEntity r)")
    RatingEntity findUserTopRatedMovie(int userid);

}
