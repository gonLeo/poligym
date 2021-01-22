package com.poligym.repository;

import java.util.Optional;

import com.poligym.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findById(int id);

    Optional<Users> findByEmail(String email);
}
