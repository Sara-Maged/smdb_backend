package com.example.smdb.Services;

import com.example.smdb.Entities.MovieEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Respiratories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieEntity> getMoviesPaginated(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<MovieEntity> pagedResult = movieRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<MovieEntity>();
        }
    }

    public List<MovieEntity> getAllMovies(){
        return movieRepository.findAllByAdultIsFalseOrderByVoteAverageDesc();
    }

    public MovieEntity findMovieById(Integer movieId) throws Exception {
        if (movieRepository.findById(movieId).isPresent())
            return movieRepository.getById(movieId);


        else throw new Exception("Movie Not Found");
    }

    public MovieEntity addNewMovie(MovieEntity movie) {
        Optional<MovieEntity> movieByName = Optional.ofNullable(movieRepository
                .findMovieEntityByTitle(movie.getTitle()));

        if(movieByName.isPresent()){
            throw new IllegalStateException("Movie Already Exist");
        }

        return movieRepository.save(movie);
    }

    public Iterable<MovieEntity> saveList(List<MovieEntity> users) {
        return movieRepository.saveAll(users);
    }

    public MovieEntity flagMovieById(Integer id) {
        MovieEntity movie = movieRepository.getById(id);

        int previousFlagNumber = movie.getFlags();
        movie.setFlags(previousFlagNumber + 1);

        if (movie.getFlags() > 10)
            movie.setAdult(true);

        return movieRepository.save(movie);
    }

    public List<MovieEntity> getFlaggedMovies() {
        return movieRepository.findAllByFlagsGreaterThan();
    }

    public MovieEntity editMovieById(Integer id, MovieEntity updatedMovie) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setRelease_date(updatedMovie.getRelease_date());
                    movie.setGenre_ids(updatedMovie.getGenre_ids());
                    movie.setOriginal_language(updatedMovie.getOriginal_language());
                    return movieRepository.save(movie);
                })
                .orElseGet(() -> {
                    updatedMovie.setId(id);
                    //updatedMovie.setSource("Admin");
                    return movieRepository.save(updatedMovie);
                });
    }
}
