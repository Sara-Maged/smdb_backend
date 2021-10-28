package com.example.smdb.Services;

import com.example.smdb.Entities.*;
import com.example.smdb.Respiratories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final RatingRepository ratingRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, MovieRepository movieRepository, GenreRepository genreRepository, RatingRepository ratingRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.ratingRepository = ratingRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //Getting All Users
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    //Getting a Specific User (By Email)
    public UserEntity getUser(String email){return userRepository.findAllByEmail(email);}

    //Adding a new User & Confirming the Email is not used Before
    public UserEntity addNewUser(UserEntity user) {
        log.info("Saving new user {} to database", user.getEmail());
        Optional<UserEntity> userByEmail = Optional.ofNullable(userRepository
                .findAllByEmail(user.getEmail()));

        if(userByEmail.isPresent()){
            throw new IllegalStateException("Email Taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //Specifying the User's Role in the System
    public void addRoleToUser(String email, String roleName) {
        UserEntity user = userRepository.findAllByEmail(email);
        RoleEntity role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
<<<<<<< HEAD

    public List<MovieEntity> getUserRecommendation(int id) {

        RatingEntity userTopRatedMovie = ratingRepository.findUserTopRatedMovie(id);
        List<GenreEntity> userTopRatedMovieGenres = genreRepository.findAllByMovieId(userTopRatedMovie.getRatingId().getMovieId());

        Set<GenreEntity> genreEntitySet = new HashSet<>();

        userTopRatedMovieGenres.forEach(
                genreEntity -> {
                    genreEntitySet.addAll(genreRepository.findAllById(genreEntity.getId()));

                }
        );

        List<MovieEntity> recommendedMovies = new ArrayList<>();
        genreEntitySet.forEach(
                movieWithGenre -> {
                    recommendedMovies.addAll(movieRepository.findAllByGenre(movieWithGenre.getGenre_id()) // 550988
//                            .add(movieRepository
//                                    .getById(movieWithGenre.getMovieId())
                            );
                }
        );

        return recommendedMovies;

    }
=======
>>>>>>> 4ceb08bf954cc10d2e8c41db5f43884bc604260d
}
