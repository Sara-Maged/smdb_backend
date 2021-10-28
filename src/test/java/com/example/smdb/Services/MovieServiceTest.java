package com.example.smdb.Services;
import com.example.smdb.Entities.MovieEntity;
import com.example.smdb.Entities.UserEntity;
import com.example.smdb.Respiratories.MovieRepository;
import com.example.smdb.Respiratories.RoleRepository;
import com.example.smdb.Respiratories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class MovieServiceTest {

    private MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    private MovieService movieService = Mockito.mock(MovieService.class);

    @Test
    void getMoviesPaginated_AllUMoviesFetched_True() {
        MovieEntity movie = new MovieEntity();
        MovieEntity movie1 = new MovieEntity();

        List<MovieEntity> movies = new ArrayList(Arrays.asList(movie, movie1));
        Mockito.when(movieService.getMoviesPaginated(1, 10)).thenReturn(movies);

        assertEquals(2, movieService.getMoviesPaginated(1, 10).size());
    }

    @Test
    void getAllMovies_AllMoviesFetched_True() {
        MovieEntity movie = new MovieEntity();
        MovieEntity movie1 = new MovieEntity();

        List<MovieEntity> movies = new ArrayList(Arrays.asList(movie, movie1));

        Mockito.when(movieService.getAllMovies()).thenReturn(movies);
        assertEquals(2, movieService.getAllMovies().size());
    }

    @Test
    void findMovieById_RequestedMovieFetched_True() throws Exception {
        MovieEntity expectedMovie = new MovieEntity();//(1, "en", "Free Guy", "movie Overview", 99.8, "poster path", null, null, false, "path", false, 9.2, 88, 3, null, null);
        expectedMovie.setId(1);
        expectedMovie.setTitle("Wonder");

        given(movieService.findMovieById(1)).willReturn(expectedMovie);
        MovieEntity actualMovie = movieService.findMovieById(1);

        assertEquals(expectedMovie, actualMovie);
    }

    @Test
    void addNewMovie_Requested_MovieCreated_True() {
        MovieEntity movie = new MovieEntity();
        movie.setTitle("Wonder");

        given(movieService.addNewMovie(movie)).willReturn(movie);
        MovieEntity movieCreated = movieService.addNewMovie(movie);

        assertThat(movieCreated.getTitle().equals(movie.getTitle()));
    }

    @Test
    void flagMovieById_FlagsNumberDiffer_True() {
        MovieEntity movie = new MovieEntity();
        movie.setFlags(1);
        MovieEntity movie1 = new MovieEntity();
        movie1.setFlags(2);

        Mockito.when(movieService.flagMovieById(1)).thenReturn(movie);

        assertFalse(movie.equals(movie1));
    }

    @Test
    void getFlaggedMovies_RequestedFlags_Fetched_True() {
        MovieEntity movie = new MovieEntity();
        MovieEntity movie1 = new MovieEntity();

        List<MovieEntity> movies = new ArrayList(Arrays.asList(movie, movie1));

        Mockito.when(movieService.getFlaggedMovies()).thenReturn(movies);
        assertEquals(2, movieService.getFlaggedMovies().size());
    }

    @Test
    void editMovieById_MovieDiffers_True() {
        MovieEntity movie = new MovieEntity();
        movie.setOriginal_language("en");

        MovieEntity updatedMovie = new MovieEntity();
        updatedMovie.setOriginal_language("ja");

        Mockito.when(movieService.editMovieById(1,updatedMovie)).thenReturn(updatedMovie);
        assertFalse(movie.equals(updatedMovie));
    }
}
