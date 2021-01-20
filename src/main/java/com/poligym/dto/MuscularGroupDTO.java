package com.poligym.dto;

import com.poligym.models.MuscularGroup;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
public class MuscularGroupDTO {
  private int id;
  private String description;

  public MuscularGroup convertDTOToEntity() {
    return new ModelMapper().map(this, MuscularGroup.class);
  }
}
