package com.poligym.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.poligym.dto.TrainningDTO;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Training extends EntityBase {

  /**
   *
   */
  private static final long serialVersionUID = 8318488844661779630L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(name = "id")
  private int id;

  @JoinColumn(name = "user_id", nullable = false)
  @ManyToOne
  private User user;

  @JoinColumn(name = "exercise_id", nullable = false)
  @OneToOne(mappedBy = "Trainning", targetEntity = Exercices.class, cascade = CascadeType.ALL)
  private Exercices exercises;

  @Column(name = "weight", nullable = false)
  private float weight;

  @Column(name = "series", nullable = false)
  private float series;

  @Column(name = "repetitions", nullable = false)
  private int repetitions;

  @Column(name = "training_validity", nullable = false)
  private Date training_validity;

  /**
   * Method to convert an Trainning entity to an User DTO
   * 
   * @author Pedro Vinicius
   * @since 17/01/2021
   * 
   * @param trainning
   * @return an TrainningDTO object
   */

  public TrainningDTO convertEntityToDTO() {
    return new ModelMapper().map(this, TrainningDTO.class);
  }
}
