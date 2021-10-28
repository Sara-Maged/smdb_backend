package com.example.smdb.Controllers;

import com.example.smdb.Entities.MovieEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Services.MovieService;
import com.example.smdb.Services.UserDetailService;
import com.example.smdb.Services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailService userDetailService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    void getAllMovies() throws Exception{
        MovieEntity movie = new MovieEntity();
        movie.setTitle("Wonder");
        MovieEntity movie1 = new MovieEntity();

        List<MovieEntity> movies = new ArrayList(Arrays.asList(movie, movie1));

        when(movieService.getAllMovies()).thenReturn(movies);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/topRatedMovies")
                        .with(SecurityMockMvcRequestPostProcessors.user("Sara@gmail.com").roles("ADMIN").password("password"))
                        .header("X-Foo", "Bearer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Wonder"));
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("password"));
    }

    @Test
    void getMoviesPaginated() throws Exception{
        MovieEntity movie = new MovieEntity();
        movie.setTitle("Wonder");
        MovieEntity movie1 = new MovieEntity();

        List<MovieEntity> movies = new ArrayList(Arrays.asList(movie, movie1));

        when(movieService.getMoviesPaginated(1, 5)).thenReturn(movies);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/movies/1/5")
                        .with(SecurityMockMvcRequestPostProcessors.user("Sara@gmail.com").roles("ADMIN").password("password"))
                        .header("X-Foo", "Bearer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Wonder"));

    }

    @Test
    void getMovieById() throws Exception{
        MovieEntity movie = new MovieEntity();
        movie.setTitle("Wonder");

        when(movieService.findMovieById(1)).thenReturn(movie);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/movie/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("Sara@gmail.com").roles("ADMIN").password("password"))
                        .header("X-Foo", "Bearer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Wonder"));

    }

    @Test
    void addNewMovie() {
    }

    @Test
    void editMovieById() {
    }

    @Test
    void flagMovieById() {
    }

    @Test
    void getFlaggedMovies() {
    }
}