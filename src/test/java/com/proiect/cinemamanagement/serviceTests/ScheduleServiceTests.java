package com.proiect.cinemamanagement.serviceTests;

import com.proiect.cinemamanagement.dao.Schedule;
import com.proiect.cinemamanagement.entity.ScheduleEntity;
import com.proiect.cinemamanagement.repository.ScheduleRepository;
import com.proiect.cinemamanagement.service.ScheduleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTests {
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleService scheduleService;

    private static Schedule schedule;

    private static List<Schedule> schedules;


    @BeforeAll
    public static void setup(){
        schedule=new Schedule("Film", "20-02-2021", "room", 200f);
        schedules = new ArrayList<Schedule>();
        schedules.add(schedule);
        Schedule schedule2=new Schedule("Film", "20-03-2021", "room2", 220f);
        schedules.add(schedule2);
    }



    @Test
    @DisplayName("Test get schedules")
    public void testGetAllSchedules(){
        doReturn(schedules).when(scheduleRepository).findAll();
        scheduleService.processGetScheduledMovies();
    }

    @Test
    @DisplayName("Test get schedule by room name")
    public void testGetSchedule(){
        ScheduleEntity scheduleEntity=new ScheduleEntity(schedule);
        doReturn(schedules).when(scheduleRepository).findByRoomNameContaining(scheduleEntity.getRoomName());
        scheduleService.processFindByRoomNameContaining(scheduleEntity.getRoomName());
    }

    @Test
    @DisplayName("Test get schedule by movie name")
    public void testGetScheduleByMovieName(){
        ScheduleEntity scheduleEntity=new ScheduleEntity(schedule);
        doReturn(schedules).when(scheduleRepository).findByMovieNameContaining(scheduleEntity.getMovieName());
        scheduleService.processFindByMovieNameContaining(scheduleEntity.getMovieName());
    }



}
