package com.poligym.dto;

import com.poligym.models.MuscularGroup;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
public class MuscularGroupDTO {
  private String description;

  public MuscularGroup convertEntityToExercises() {
    return new ModelMapper().map(this, MuscularGroup.class);
  }
}
