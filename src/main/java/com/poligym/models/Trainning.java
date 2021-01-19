package com.poligym.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Trainning extends EntityBase {

  /**
   *
   */
  private static final long serialVersionUID = 8318488844661779630L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(name = "id")
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  private User users;

  @JoinColumn(name = "trainning_id")
  @OneToMany(targetEntity = Exercises.class, cascade = CascadeType.ALL)
  private List<Exercises> exercises;

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
