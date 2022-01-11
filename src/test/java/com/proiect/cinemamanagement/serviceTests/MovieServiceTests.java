package com.proiect.cinemamanagement.serviceTests;

import com.proiect.cinemamanagement.dao.Movie;
import com.proiect.cinemamanagement.entity.MovieEntity;
import com.proiect.cinemamanagement.repository.MovieRepository;
import com.proiect.cinemamanagement.service.MovieService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTests {
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieService movieService;

    private static Movie movie;

    private static List<Movie> movies;


    @BeforeAll
    public static void setup(){
        movie=new Movie("Film", "2h", "horror", "Actor1", "Director1", 200f);
        movies = new ArrayList<Movie>();
        movies.add(movie);
        Movie movie2=new Movie("Film2", "2h", "comedie", "Actor2", "Director2", 2030f);
        movies.add(movie2);
    }

    @Test
    @DisplayName("Test save movie")
    public void testSaveMovie(){
        MovieEntity movieEntity=new MovieEntity(movie);
        doReturn(movieEntity).when(movieRepository).save(movieEntity);
        movieService.processPostMovie(movie);
    }

    @Test
    @DisplayName("Test get movies")
    public void testGetAllMovies(){
        doReturn(movies).when(movieRepository).findAll();
        movieService.processGetMovies(1,1,"id");
    }

    @Test
    @DisplayName("Test get movie by Name")
    public void testGetMovieByName(){
        MovieEntity movieEntity=new MovieEntity(movie);
        doReturn(movies).when(movieRepository).findByNameContaining(movieEntity.getName());
        movieService.processFindByNameContaining(movieEntity.getName());
    }



    @Test
    @DisplayName("Test delete movie")
    public void testDeleteMovie(){
        MovieEntity movieEntity=new MovieEntity(movie);
        doReturn(Optional.empty()).when(movieRepository).findById(movieEntity.getId());
        movieService.processFinfById(movieEntity.getId());
    }
}
