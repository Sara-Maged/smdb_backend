package com.example.smdb.Respiratories;

import com.example.smdb.Entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {
<<<<<<< HEAD
    //List<RatingEntity> findAllByMovieEntity_IdWithin(int id);

//    List<RatingEntity> findAllByRatingIdUserIdWithin();
//    List<RatingEntity> getAllByRatingIdUserIdWithin(int id);

//    @Query("select r from RatingEntity r where r.ratingId.userId = id order by r.rating")
//    List<RatingEntity> findByUserEntityIdWithinOrderByRating(Integer id);

    @Query("select ratnigEntity from RatingEntity ratnigEntity where ratnigEntity.ratingId.userId = ?1 and ratnigEntity.rating = (select max (r.rating) from ratnigEntity r)")
    RatingEntity findUserTopRatedMovie(int userid);



=======
>>>>>>> 4ceb08bf954cc10d2e8c41db5f43884bc604260d
}
