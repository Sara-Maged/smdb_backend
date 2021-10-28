package com.example.smdb.Controllers;

import com.example.smdb.Entities.RatingEntity;
import com.example.smdb.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @GetMapping("admin/getRatings")
    public ResponseEntity<List<RatingEntity>> getRatings(){
        return ResponseEntity.ok().body(ratingService.getRatings());
    }

    @PostMapping("user/addRating")
    public void addRating(@RequestBody RatingEntity rating){
        ResponseEntity.ok().body(ratingService.userRate(rating.getRatingId().getUserId(), rating.getRatingId().getMovieId(),
                rating.getRating()));
    }
}
