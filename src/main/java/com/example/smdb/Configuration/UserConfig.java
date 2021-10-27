//package com.example.smdb;
//
//import com.example.smdb.Entities.UserEntity;
//import com.example.smdb.Respiratories.UserRepository;
//import org.aspectj.apache.bcel.Repository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class UserConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository){
//        return args -> {
//            UserEntity lobna = new UserEntity(
//                    1,
//                    "Lobna@gmail.com",
//                    "password",
//                    "USER",
//                    true
//            );
//
//            UserEntity sara = new UserEntity(
//                    "sara@gmail.com",
//                    "password",
//                    "ADMIN",
//                    true
//            );
//
//            repository.saveAll(
//                    List.of(lobna, sara)
//            );
//        };
//    }
//}
