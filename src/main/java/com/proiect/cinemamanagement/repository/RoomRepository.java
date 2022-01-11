package com.proiect.cinemamanagement.repository;

import com.proiect.cinemamanagement.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
 Iterable<RoomEntity> findByNameContaining(String name);
}
