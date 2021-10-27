package com.example.smdb.Services;

import com.example.smdb.Entities.MovieEntity;
import com.example.smdb.Entities.RatingEntity;
import com.example.smdb.Entities.RoleEntity;
import com.example.smdb.Respiratories.RatingRepository;
import com.example.smdb.Respiratories.RoleRepository;
import com.example.smdb.Respiratories.UserRepository;
import com.example.smdb.Entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RatingRepository ratingRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, RatingRepository ratingRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

//    public List<MovieEntity> getUserRecommendation(Integer id) {
//        UserEntity user = userRepository.getById(id);
//        Collection<RatingEntity> userRatings = user.getRatings();
//    }
}
