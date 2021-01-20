package com.poligym.dto;

import com.poligym.models.Exercises;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
public class ExercisesDTO {
  private int id;
  private String equipment;
  private String description;
  private Integer muscularGroupId;

  public Exercises convertDTOToEntity() {
    return new ModelMapper().map(this, Exercises.class);
  }

}
