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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.poligym.dto.TrainningDTO;

import org.modelmapper.ModelMapper;

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
public class Training extends EntityBase {

  /**
   *
   */
  private static final long serialVersionUID = 8318488844661779630L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(name = "id")
  private int id;

  @JoinColumn(name = "user_id")
  @ManyToOne
  private User user;

  @JoinColumn(name = "exercise_id")
  @OneToMany(mappedBy = "Trainning", targetEntity = Exercices.class, cascade = CascadeType.ALL)
  private Exercices exercises;

  @NotBlank
  @Size(max = 50)
  @Column(name = "weight")
  private float weight;

  @NotBlank
  @Size(max = 50)
  @Column(name = "series")
  private float series;

  @NotBlank
  @Size(max = 50)
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
