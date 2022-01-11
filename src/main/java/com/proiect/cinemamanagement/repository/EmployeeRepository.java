package com.proiect.cinemamanagement.repository;

import com.proiect.cinemamanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    Iterable<EmployeeEntity> findByFirstNameContaining(String name);
}
