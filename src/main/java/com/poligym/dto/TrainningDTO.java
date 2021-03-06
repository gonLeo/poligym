package com.poligym.dto;


import javax.validation.constraints.NotNull;

import com.poligym.models.Trainning;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class that implements Trainning Data Transfer Object (DTO).
 * 
 * @author Pedro Vinicius
 * @since 18/01/2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TrainningDTO {

  private int id;

  @NotNull(message = "User id cannot be a null")
  private int usersId;

  @NotNull(message = "Exercise id cannot be a null")
  private int exercisesId;

  @NotNull(message = "Section id cannot be a null")
  private int sectionId;

  @NotNull(message = "Weight cannot be a null")
  private float weight;

  @NotNull(message = "series cannot be a null")
  private float series;

  @NotNull(message = "repetitions cannot be a null")
  private int repetitions;

  @NotNull(message = "rest cannot be a null")
  private int rest;  

  /**
   * Method to convert an TrainningDTO to a Travel Entity
   * 
   * @author Pedro Vinicius
   * @since 17/01/2021
   * 
   * @param trainning
   * @return an TrainningDTO object
   */

  public Trainning convertDTOToEntity() {
    return new ModelMapper().map(this, Trainning.class);
  }

}
