package com.example.smdb;

//import com.example.smdb.Respiratories.UserRepository;
import com.example.smdb.Entities.MovieEntity;
import com.example.smdb.Entities.RoleEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Services.MovieService;
import com.example.smdb.Services.RoleService;
import com.example.smdb.Services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@Slf4j
public class SmdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmdbApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService){
        if(userService.getUsers().size() == 0){
            return args -> {
                userService.addNewUser(new UserEntity("Lobna@gmail.com", "password", true, new ArrayList<>(), new ArrayList<>()));
                userService.addRoleToUser("Lobna@gmail.com", "ROLE_USER");
            };
        }

        if(roleService.getRoles().size() == 0){
            return args -> {
                userService.addRoleToUser("Lobna@gmail.com", "ROLE_USER");
            };
        }
        else {
            log.info("User & Role Database Already Populated");
            return null;
        }
    }

   @Bean
    CommandLineRunner runner(MovieService movieService) {
        if(movieService.getAllMovies().size() == 0){
            return args -> {
                // read json and write to db
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<MovieEntity>> typeReference = new TypeReference<List<MovieEntity>>(){};
                InputStream inputStream = TypeReference.class.getResourceAsStream("/json/movies.json");
                try {
                    List<MovieEntity> movies = mapper.readValue(inputStream,typeReference);
                    movieService.saveList(movies);
                    System.out.println("Users Saved!");
                } catch (IOException e){
                    System.out.println("Unable to save users: " + e.getMessage());
                }
            };
        }
        else {
            log.info("Movie Database Already Populated");
            return null;
        }
    }

}
