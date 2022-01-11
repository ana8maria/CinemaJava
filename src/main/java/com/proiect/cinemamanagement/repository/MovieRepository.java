package com.proiect.cinemamanagement.repository;

import com.proiect.cinemamanagement.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

   Iterable<MovieEntity> findByNameContaining(String name);

   @Query(value = "select distinct name from movie", nativeQuery = true)
   Iterable<String> findMovieName();
}
