package com.poligym.repository;

import java.util.Optional;

import com.poligym.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
}
