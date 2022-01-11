package com.proiect.cinemamanagement.rest;

import com.proiect.cinemamanagement.dao.Movie;
import com.proiect.cinemamanagement.entity.MovieEntity;
import com.proiect.cinemamanagement.service.MovieService;
import com.proiect.cinemamanagement.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cm/api/v1/movie")
public class MovieController {

    //create object log (type logger) to show info about requests
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    public MovieService movieService;
    public TicketService ticketService;

    public MovieController(MovieService movieService, TicketService ticketService) {
        this.movieService = movieService;
        this.ticketService=ticketService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize(" hasRole('ROLE_ADMIN') ")
    @PostMapping()
    public ResponseEntity<MovieEntity> createMovie(@Valid @RequestBody Movie movie){
        log.info("Received request to create movie: {}", movie);
        MovieEntity savedMovieEntity = movieService.processPostMovie(movie);
        return new ResponseEntity<>(savedMovieEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<Iterable<MovieEntity>> getMovies(
            @RequestParam(required = false) String name,  @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        Iterable<MovieEntity> returnedMovieEntity=null;
        if (name == null) {
            log.info("Received request to get movies");
            returnedMovieEntity = movieService.processGetMovies(pageNo, pageSize, sortBy);
            log.info("returned {}", returnedMovieEntity);
        } else {
            log.info("Received request to get movies by name: " + name);
            returnedMovieEntity = movieService.processFindByNameContaining(name);
        }
        if (returnedMovieEntity == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Movie not found in the database");
        }

        return new ResponseEntity<>(returnedMovieEntity, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MovieEntity> getMovie(@PathVariable Integer id) {
        log.info("Received request to get movie with id: {}", id);
        MovieEntity returnedMovieEnitty = movieService.processFinfById(id);
        log.info("Created returnedMovieEnitty with id: {}", id);
        if (returnedMovieEnitty == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Movei with campaign id: " + id + " not found in the database");
        }
        log.debug("before return");
        return new ResponseEntity<>(returnedMovieEnitty, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/report")
    public ResponseEntity<String> getMovieReport() throws ParseException, JSONException {
        Iterable<String> movieNameList;
        List<Integer> noOfTickets=new ArrayList<>();
        movieNameList=movieService.getAllMovieName();
        for (String movie:movieNameList){
            noOfTickets.add(ticketService.findTicketNoByMovieName(movie));
        }
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("movieName", movieNameList);
        jsonObject.put("noOfTickets", noOfTickets);

        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
    }

}
