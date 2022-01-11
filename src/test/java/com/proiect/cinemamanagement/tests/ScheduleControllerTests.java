package com.proiect.cinemamanagement.tests;

import com.proiect.cinemamanagement.entity.ScheduleEntity;
import com.proiect.cinemamanagement.service.ScheduleService;
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
public class ScheduleControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduleService scheduleService;

    @Test
    public void testGetSchedules() throws Exception{
        ArrayList<ScheduleEntity> list = new ArrayList<>();

        Assert.assertNotNull(scheduleService);

        ScheduleEntity schedule= new ScheduleEntity(1, "firstName", "12-12-2020", "roomName",3f);
        ScheduleEntity schedule2= new ScheduleEntity(2, "firstName2", "12-11-2020", "roomName",3.2f);
        list.add(schedule);
        list.add(schedule2);
        ObjectMapper mapper = new ObjectMapper();
        given(this.scheduleService.processGetScheduledMovies()).willReturn(list);
        this.mvc
                .perform(get("/cm/api/v1/schedule").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void testFindByMovieName() throws Exception {
        ArrayList<ScheduleEntity> list = new ArrayList<>();

        Assert.assertNotNull(scheduleService);


        ScheduleEntity schedule =
                new ScheduleEntity(
                        1, "firstName", "12-12-2020", "roomName",3f);

        ScheduleEntity schedule2 =
                new ScheduleEntity(
                        2, "firstName2", "12-11-2020", "roomName",3.2f);

        list.add(schedule);
        list.add(schedule2);

        ObjectMapper mapper = new ObjectMapper();


        given(this.scheduleService.processFindByMovieNameContaining("firstName")).willReturn(list);
        this.mvc
                .perform(get("/cm/api/v1/schedule?movieName=firstName").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void testFindByRoomName() throws Exception {
        ArrayList<ScheduleEntity> list = new ArrayList<>();

        Assert.assertNotNull(scheduleService);


        ScheduleEntity schedule =
                new ScheduleEntity(
                        1, "firstName", "12-12-2020", "roomName",3f);

        ScheduleEntity schedule2 =
                new ScheduleEntity(
                        2, "firstName2", "12-11-2020", "roomName2",3.2f);

        list.add(schedule);
        list.add(schedule2);

        ObjectMapper mapper = new ObjectMapper();


        given(this.scheduleService.processFindByRoomNameContaining("roomName")).willReturn(list);
        this.mvc
                .perform(get("/cm/api/v1/schedule?roomName=roomName").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

//    @Test
//    public void testFindByStartDate() throws Exception {
//        ArrayList<ScheduleEntity> list = new ArrayList<>();
//
//        Assert.assertNotNull(scheduleService);
//
//
//        ScheduleEntity schedule =
//                new ScheduleEntity(
//                        1, "firstName", "12-12-2020", "roomName",3f);
//
//        ScheduleEntity schedule2 =
//                new ScheduleEntity(
//                        2, "firstName2", "12-11-2020", "roomName2",3.2f);
//
//        list.add(schedule);
//        list.add(schedule2);
//
//        ObjectMapper mapper = new ObjectMapper();
//        Date now = new Date();
//
//        given(this.scheduleService.processFindByStartDateContaining(now)).willReturn(list);
//        this.mvc
//                .perform(get("/vms/api/v1/schedule?startDate=now").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(mapper.writeValueAsString(list)));
//    }

}
