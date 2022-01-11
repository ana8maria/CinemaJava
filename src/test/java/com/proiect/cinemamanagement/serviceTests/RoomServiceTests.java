package com.proiect.cinemamanagement.serviceTests;

import com.proiect.cinemamanagement.dao.Room;
import com.proiect.cinemamanagement.entity.RoomEntity;
import com.proiect.cinemamanagement.repository.RoomRepository;
import com.proiect.cinemamanagement.service.RoomService;
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
public class RoomServiceTests {
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private RoomService roomService;

    private static Room room;

    private static List<Room> rooms;


    @BeforeAll
    public static void setup(){
        room=new Room("Room", 200, 20, 10);
        rooms = new ArrayList<Room>();
        rooms.add(room);
        Room room2=new Room("Room2", 20, 5, 4);;
        rooms.add(room2);
    }

    @Test
    @DisplayName("Test save room")
    public void testSaveRoom(){
        RoomEntity roomEntity=new RoomEntity(room);
        doReturn(roomEntity).when(roomRepository).save(roomEntity);
        roomService.processPostRooms(room);
    }

    @Test
    @DisplayName("Test get rooms")
    public void testGetAllRooms(){
        doReturn(rooms).when(roomRepository).findAll();
        roomService.processGetRooms(1,1,"id");
    }

    @Test
    @DisplayName("Test get room")
    public void testGetRoom(){
        RoomEntity roomEntity=new RoomEntity(room);
        doReturn(rooms).when(roomRepository).findByNameContaining(roomEntity.getName());
        roomService.processFindByNameContaining(roomEntity.getName());
    }



}
