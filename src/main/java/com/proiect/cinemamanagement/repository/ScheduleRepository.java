package com.proiect.cinemamanagement.repository;

import com.proiect.cinemamanagement.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    Iterable<ScheduleEntity> findByStartDateContaining(Date startDate);

    Iterable<ScheduleEntity> findByMovieNameContaining(String movieName);

    Iterable<ScheduleEntity> findByRoomNameContaining(String roomName);
}
