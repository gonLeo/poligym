package com.poligym.dto;

import com.poligym.models.Exercises;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
public class ExercisesDTO {
  private String equipment;
  private String muscleGroup;
  private String description;

  public Exercises convertEntityToExercises() {
    return new ModelMapper().map(this, Exercises.class);
  }

}
