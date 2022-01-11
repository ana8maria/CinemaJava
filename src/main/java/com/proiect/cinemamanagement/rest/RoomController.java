package com.proiect.cinemamanagement.rest;

import com.proiect.cinemamanagement.dao.Room;
import com.proiect.cinemamanagement.entity.RoomEntity;
import com.proiect.cinemamanagement.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/cm/api/v1/rooms")
public class RoomController {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);
    public RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<RoomEntity> createRoom(@Valid @RequestBody Room room){
        log.info("Received request to create room: {}", room);
        RoomEntity savedRoomEntity = roomService.processPostRooms(room);
        return new ResponseEntity<>(savedRoomEntity, HttpStatus.CREATED);
    }

    //TODO sa separ in doua metode diferite, iar cea care e byName sa returneze un singur obiect +FE

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<Iterable<RoomEntity>> getRooms( @RequestParam(required = false) String name,  @RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                          @RequestParam(defaultValue = "id") String sortBy){
        Iterable<RoomEntity> returnedRoomEntity=null;


        if (name == null) {
            returnedRoomEntity = roomService.processGetRooms(pageNo, pageSize, sortBy);


        }else {
            returnedRoomEntity=roomService.processFindByNameContaining(name);
        }
        if(returnedRoomEntity==null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room not found in the database");
        }
        return new ResponseEntity<>(returnedRoomEntity, HttpStatus.OK);

    }

}
