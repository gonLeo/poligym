package com.poligym.dto;

import com.poligym.models.Exercises;
import com.poligym.models.MuscularGroup;
import com.poligym.repository.ExercisesRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class ExercisesDTO {

  @Autowired
  private ExercisesRepository exercisesRepository;

  private Integer id;
  private String equipment;
  private String description;
  private Integer muscularGroupId;

  public Exercises convertEntityToExercises() {
    return new ModelMapper().map(this, Exercises.class);
  }

}
