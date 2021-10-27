package com.example.smdb.Services;

import com.example.smdb.Controllers.UserController;
import com.example.smdb.Entities.RoleEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Respiratories.RoleRepository;
import com.example.smdb.Respiratories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    private UserService userService = Mockito.mock(UserService.class);



    @Test
    @DisplayName("Test Should Pass When it Retrieves all users")
    void getUsers_AllUsersFetched_True(){

        UserEntity sara = new UserEntity("Sara@gmail.com", "password", true, null, null);
        UserEntity aly = new UserEntity("Aly@gmail.com", "password", true, null, null);

        List<UserEntity> users = new ArrayList<>(Arrays.asList(aly, sara));

        Mockito.when(userService.getUsers()).thenReturn(users);
        assertEquals(2, userService.getUsers().size());

    }

    @Test
    @DisplayName("Should find user by Email")
    void getUser_RequestedUserFetched_True() {
        UserEntity sara = new UserEntity("saraMaged@gmail.com", "password", true, null, null);
        UserEntity user = new UserEntity();

        Mockito.when(userService.getUser(user.getEmail())).thenReturn(sara);
        assertEquals(userService.getUser(user.getEmail()), sara);
    }

    @Test
    void addNewUser_RequestedUserCreated_True() {
        UserEntity user = new UserEntity();
        user.setEmail("Sara@gmail.com");

        given(userService.addNewUser(user)).willReturn(user);
        UserEntity userCreated = userService.addNewUser(user);

        assertThat(userCreated.getEmail().equals(user.getEmail()));
    }

    @Test
    void addRoleToUser_MethodCalled_True() {

        RoleEntity role = new RoleEntity("ADMIN");
        List<RoleEntity> roles = new ArrayList<>(Arrays.asList(role));

        UserEntity sara = new UserEntity("sara@gmail.com", "password", true, roles, null);

        Mockito.when(userRepository.findAllByEmail("sara@gmail.com")).thenReturn(sara);
        Mockito.when(roleRepository.findByName("ADMIN")).thenReturn(role);

        UserEntity user = userRepository.findAllByEmail("sara@gmail.com");
        RoleEntity userRole = roleRepository.findByName("ADMIN");

        user.getRoles().add(userRole);

        userService.addRoleToUser("sara@gmail.com", "ADMIN");

        assertEquals(sara, user);
        assertEquals(role, userRole);
        verify(userService, times(1)).addRoleToUser("sara@gmail.com", "ADMIN");

    }
}