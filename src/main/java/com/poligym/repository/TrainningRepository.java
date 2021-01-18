package com.poligym.repository;

import java.util.List;
import java.util.Optional;

import com.poligym.models.Trainning;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TrainningRepository extends JpaRepository<Trainning, Long>{
    
    Optional<List<Trainning>> findByUserId(int id);
}
