package com.proiect.cinemamanagement.rest;

import com.proiect.cinemamanagement.entity.ScheduleEntity;
import com.proiect.cinemamanagement.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;

@RestController
@RequestMapping("/cm/api/v1/schedule")
public class ScheuldeController {
    private static final Logger log = LoggerFactory.getLogger(ScheuldeController.class);

    public ScheduleService scheduleService;

    public ScheuldeController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<Iterable<ScheduleEntity>> getScheduledMovies(@RequestParam(required = false) String movieName,
                                                             @RequestParam(required = false) String roomName,
                                                             @RequestParam(required = false) Date startDate){
        Iterable<ScheduleEntity> filterdScheduledMovies=null;

        if(movieName!=null){
            filterdScheduledMovies=scheduleService.processFindByMovieNameContaining(movieName);
        }
        if(roomName!=null){
            filterdScheduledMovies=scheduleService.processFindByRoomNameContaining(roomName);

        }
        if(startDate!=null){
            filterdScheduledMovies=scheduleService.processFindByStartDateContaining(startDate);
        }
        if(movieName==null &&roomName==null && startDate==null){
            filterdScheduledMovies=scheduleService.processGetScheduledMovies();
        }
        return new ResponseEntity<>(filterdScheduledMovies, HttpStatus.OK);
    }



}
