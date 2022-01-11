package com.proiect.cinemamanagement.service;

import com.proiect.cinemamanagement.dao.Movie;
import com.proiect.cinemamanagement.entity.MovieEntity;
import com.proiect.cinemamanagement.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //post movie
    
    public MovieEntity processPostMovie(Movie movie){
        if(movie.getActors()==null || movie.getCost()==null || movie.getDirector()==null || movie.getDuration()==null || movie.getName()==null || movie.getType()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,  "Insufficient pices of information about the movie");
        }
        MovieEntity movieEntity = new MovieEntity(movie);

        return movieRepository.save(movieEntity);
    }

    //get all movies
    public Iterable<MovieEntity> processGetMovies(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<MovieEntity> pageResult = movieRepository.findAll(paging);

        log.info("Get all movies {}", movieRepository.findAll());


        return pageResult.getContent();

    }

    //get one movie by id

    public MovieEntity processFinfById(Integer id){
        return movieRepository.findById(id).orElse(null);
    }


    public Iterable<MovieEntity> processFindByNameContaining (String name){
        return movieRepository.findByNameContaining(name);
    }

    //get all movies name

    public Iterable<String> getAllMovieName(){
        return movieRepository.findMovieName();
    }


}
