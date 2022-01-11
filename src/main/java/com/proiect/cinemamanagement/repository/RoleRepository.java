package com.proiect.cinemamanagement.repository;

import com.proiect.cinemamanagement.dao.ERole;
import com.proiect.cinemamanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}