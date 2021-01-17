package com.poligym.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.poligym.dto.TrainningDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that implements an Trainning entity in the API.
 * 
 * @author Pedro Vinicius
 * @since 17/01/2021
 */

@Entity
@Table(name = "Trainning")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 8318488844661779630L;

  @GeneratedValue
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "user_id")
  private int user_id;

  @Column(name = "exercise_id")
  private int exercise_id;

  @Column(name = "weight")
  private float weight;

  @Column(name = "series")
  private float series;

  @Column(name = "training_validity")
  private Date training_validity;

  /**
   * Method to convert an Trainning entity to an User DTO
   * 
   * @author Pedro Vinicius
   * @since 17/01/2021
   * 
   * @param person
   * @return an TrainningDTO object
   */

  public TrainningDTO convertEntityToDTO() {
    return new ModelMapper().map(this, TrainningDTO.class);
  }
}
