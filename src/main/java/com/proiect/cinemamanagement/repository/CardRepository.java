package com.proiect.cinemamanagement.repository;

import com.proiect.cinemamanagement.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {
}
