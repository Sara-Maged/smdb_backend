package com.example.smdb.Controllers;

import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Security.SecurityConfiguration;
import com.example.smdb.Services.UserDetailService;
import com.example.smdb.Services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest{

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailService userDetailService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    //@WithMockUser(username = "Sara@gmailcom", password = "password", roles = {"ROLE_ADMIN"})
    protected void getUsers() throws Exception{
        UserEntity sara = new UserEntity("Sara@gmail.com", "password", true, null, null);
        UserEntity aly = new UserEntity("Aly@gmail.com", "password", true, null, null);

        List<UserEntity> users = new ArrayList<>(Arrays.asList(aly, sara));

        when(userService.getUsers()).thenReturn(users);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/users")
                        .with(SecurityMockMvcRequestPostProcessors.user("Sara@gmail.com").roles("ADMIN").password("password"))
                        .header("X-Foo", "Bearer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("Aly@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("password"));
    }

    @Test
    void addNewUser() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/addUser")
                        .with(SecurityMockMvcRequestPostProcessors.user("Sara@gmail.com").roles("ADMIN").password("password"))
                        .contentType(APPLICATION_JSON)
                        .content("{\"email\": \"sara@gmail.com\", \"password\":\"password\"}")
                        .with(csrf())
                )
                .andExpect(status().isOk());

        verify(userService).addNewUser(any(UserEntity.class));

    }

    @Test
    void addRoleToUser() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/addRoleToUser")
                        .with(SecurityMockMvcRequestPostProcessors.user("Sara@gmail.com").roles("ADMIN").password("password"))
                        .contentType(APPLICATION_JSON)
                        .content("{\"email\": \"sara@gmail.com\", \"roleName\":\"ADMIN\"}")
                        .with(csrf())
                )
                .andExpect(status().isOk());

        verify(userService).addRoleToUser("sara@gmail.com", "ADMIN");
    }
}
