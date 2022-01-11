package com.proiect.cinemamanagement.tests;

import com.proiect.cinemamanagement.dao.Room;
import com.proiect.cinemamanagement.entity.RoomEntity;
import com.proiect.cinemamanagement.service.RoomService;
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
public class RoomControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void testPostRoom() throws Exception {

        Assert.assertNotNull(roomService);
        ObjectMapper mapper = new ObjectMapper();

        Room room= new Room("name", 1200, 13, 5);
        RoomEntity convertedRoomEntity = new RoomEntity(room);

        given(this.roomService.processPostRooms(room)).willReturn(convertedRoomEntity);

        this.mvc.perform(post("/cm/api/v1/rooms").content(mapper.writeValueAsString(room))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(mapper.writeValueAsString(convertedRoomEntity)));

    }

    @Test
    public void testGetRooms() throws Exception{
        ArrayList<RoomEntity> list = new ArrayList<>();

        Assert.assertNotNull(roomService);

        RoomEntity room= new RoomEntity(1, "name", 1200, 13, 5);
        RoomEntity room2= new RoomEntity(2, "name", 120, 10, 5);
        list.add(room);
        list.add(room2);
        ObjectMapper mapper = new ObjectMapper();
        given(this.roomService.processGetRooms(1,1,"id")).willReturn(list);
        this.mvc
                .perform(get("/cm/api/v1/rooms").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }


}
