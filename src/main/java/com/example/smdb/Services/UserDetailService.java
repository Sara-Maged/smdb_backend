package com.example.smdb.Services;

import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Respiratories.UserRepository;
//import com.example.smdb.Security.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service

public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findAllByEmail(email);

        if(user == null){
            //log.error("User not Found in the Database");
            throw new UsernameNotFoundException("User not Found in the Database");
        }
        else {
            //log.info("{} found in the Database", email);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roleEntity -> {
            authorities.add(
                new SimpleGrantedAuthority(roleEntity.getName())
            );
        });
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

}
