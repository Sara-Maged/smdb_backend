package com.example.smdb.Respiratories;

import com.example.smdb.Entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {
    //List<RatingEntity> findAllByMovieEntity_IdWithin(int id);

//    List<RatingEntity> findAllByRatingIdUserIdWithin();
//    List<RatingEntity> getAllByRatingIdUserIdWithin(int id);



}
