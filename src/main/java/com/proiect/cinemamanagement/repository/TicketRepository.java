package com.proiect.cinemamanagement.repository;


import com.proiect.cinemamanagement.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    @Query(value = "SELECT *  FROM ticket where movie_name=:movieName and date_and_time=:dateAndTime", nativeQuery = true)
    Iterable<TicketEntity> findByMovieNameAndDateAndTimeContaining(@Param("movieName") String movieName, @Param("dateAndTime") String dateAndTime);

    @Query(value = "SELECT *  FROM ticket where  STR_TO_DATE(date_and_time, '%d/%m/%Y')>=:startDate and STR_TO_DATE(date_and_time, '%d/%m/%Y')<=:endDate", nativeQuery = true)
    Iterable<TicketEntity> findByDateAndTimeInterval(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select count(id) from ticket where movie_name=:movieName", nativeQuery = true)
    Integer findNoOfTIcketsByMovieName(@Param("movieName") String movieName);


}
