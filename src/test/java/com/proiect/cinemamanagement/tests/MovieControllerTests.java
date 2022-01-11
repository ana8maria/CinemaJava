package com.proiect.cinemamanagement.tests;
import com.proiect.cinemamanagement.dao.Movie;
import com.proiect.cinemamanagement.entity.MovieEntity;
import com.proiect.cinemamanagement.service.MovieService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void testPostMovie() throws Exception {

        Assert.assertNotNull(movieService);
        ObjectMapper mapper = new ObjectMapper();

        Movie movie= new Movie("movieName", "2h", "horror","actor1", "Director", 12.4f);
        MovieEntity convertedMovieEntity = new MovieEntity(movie);

        given(this.movieService.processPostMovie(movie)).willReturn(convertedMovieEntity);

        this.mvc.perform(post("/cm/api/v1/movie").content(mapper.writeValueAsString(movie))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(mapper.writeValueAsString(convertedMovieEntity)));

    }

    @Test
    public void testGetMovies() throws Exception{
        ArrayList<MovieEntity> list = new ArrayList<>();

        Assert.assertNotNull(movieService);

        MovieEntity movie= new MovieEntity(1, "movieName", "2h", "horror","actor1", "Director", 12.4f);
        MovieEntity movie2= new MovieEntity(2, "movieName2", "12h", "horror","actor12", "Director2", 12.45f);
        list.add(movie);
        list.add(movie2);
        ObjectMapper mapper = new ObjectMapper();
        given(this.movieService.processGetMovies(1,1,"id")).willReturn(list);
        this.mvc
                .perform(get("/cm/api/v1/movie").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void testGetMovie() throws Exception {

        int id = 1;
        Assert.assertNotNull(movieService);


        Movie movie =
                new Movie(
                        "movieName", "2h", "horror","actor1", "Director", 12.4f);

        MovieEntity convertedMovie = new MovieEntity(movie);

        ObjectMapper mapper = new ObjectMapper();

        given(this.movieService.processFinfById(id)).willReturn(convertedMovie);

        this.mvc
                .perform(
                        get("http://localhost:8080/cm/api/v1/movie/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(convertedMovie)));
    }

 

}
