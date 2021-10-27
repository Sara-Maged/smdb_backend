package com.example.smdb.Respiratories;

import com.example.smdb.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    //SELECT * FROM user_entity WHERE email = ? //@Query("SELECT user FROM UserEntity user WHERE user.email = ?1")

    UserEntity findAllByEmail(String email);

}
