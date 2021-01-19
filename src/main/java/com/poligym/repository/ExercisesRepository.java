package com.poligym.repository;

import java.util.Optional;

import com.poligym.models.Exercises;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisesRepository extends JpaRepository<Exercises, Integer> {

    Optional<Exercises> findById(int id);
}
