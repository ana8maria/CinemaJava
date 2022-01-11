package com.proiect.cinemamanagement.service;

import com.proiect.cinemamanagement.entity.ScheduleEntity;
import com.proiect.cinemamanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Iterable<ScheduleEntity> processFindByMovieNameContaining(String movieName){
        return scheduleRepository.findByMovieNameContaining(movieName);
    }

    public Iterable<ScheduleEntity> processFindByRoomNameContaining(String roomName){
        return scheduleRepository.findByRoomNameContaining(roomName);
    }

    public Iterable<ScheduleEntity> processFindByStartDateContaining(Date startDate){
        return scheduleRepository.findByStartDateContaining(startDate);

    }
    public Iterable<ScheduleEntity> processGetScheduledMovies(){return scheduleRepository.findAll();}

}
