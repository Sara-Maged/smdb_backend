package com.example.smdb.Controllers;

import com.example.smdb.Entities.MovieEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Services.MovieService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("user/topRatedMovies")
    public ResponseEntity<List<MovieEntity>> getAllMovies(){
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }

    @GetMapping("user/movies/{pageNo}/{pageSize}")
    public ResponseEntity<List<MovieEntity>> getMoviesPaginated(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize)
    {
        List<MovieEntity> list = movieService.getMoviesPaginated(pageNo, pageSize);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("user/movie/{id}")
    public ResponseEntity<MovieEntity> getMovieById (@PathVariable("id") Integer id) throws Exception {
        MovieEntity movie = movieService.findMovieById(id);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @PostMapping("admin/addMovie")
    public ResponseEntity<MovieEntity> addNewUser(@RequestBody MovieEntity movie){
        return ResponseEntity.ok().body(movieService.addNewMovie(movie));
    }

    @PutMapping("admin/editMovie/{id}")
    public ResponseEntity<MovieEntity> editMovieById(@RequestBody MovieEntity updatedMovie, @PathVariable("id") Integer id){
        //MovieEntity movie = movieService.editMovieById(id);
        return ResponseEntity.ok().body(movieService.editMovieById(id, updatedMovie));
    }

    @PutMapping("user/flagMovie/{id}")
    public ResponseEntity<MovieEntity> flagMovieById(@PathVariable("id") Integer id){
        MovieEntity movie = movieService.flagMovieById(id);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @GetMapping("admin/flaggedMovies")
    public ResponseEntity<List<MovieEntity>> getFlaggedMovies(){
        return ResponseEntity.ok().body(movieService.getFlaggedMovies());
    }

//    @PutMapping("user/addRating")
//    public ResponseEntity<List<MovieEntity>> updateAverageRating(){
//        return ResponseEntity.ok().body(movieService.updateAverageRating());
//    }

}
