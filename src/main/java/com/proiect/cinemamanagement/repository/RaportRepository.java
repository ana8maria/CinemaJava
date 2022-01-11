package com.proiect.cinemamanagement.repository;

import com.proiect.cinemamanagement.entity.RaportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaportRepository extends JpaRepository<RaportEntity, Integer> {
}
