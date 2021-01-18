package com.poligym.repository;

import com.poligym.models.Exercices;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisesRepository extends JpaRepository<Exercices, Integer> {

}
