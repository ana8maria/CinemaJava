package com.proiect.cinemamanagement.service;

import com.proiect.cinemamanagement.dao.Room;
import com.proiect.cinemamanagement.entity.RoomEntity;
import com.proiect.cinemamanagement.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomEntity processPostRooms(Room room){
        RoomEntity roomEntity = new RoomEntity(room);
        if(roomEntity.getSeatsNumber()*roomEntity.getRowNumber()!=roomEntity.getCapacity()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,  "Row number*Seats number is not equal to capacity");
        }

        return roomRepository.save(roomEntity);
    }

    public Iterable<RoomEntity> processGetRooms(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<RoomEntity> pageResult = roomRepository.findAll(paging);
        return pageResult.getContent();


    }

    public Iterable<RoomEntity> processFindByNameContaining(String name){
        return  roomRepository.findByNameContaining(name);
    }
}
