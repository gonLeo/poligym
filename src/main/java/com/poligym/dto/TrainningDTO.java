package com.poligym.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.poligym.models.Exercises;
import com.poligym.models.Trainning;
import com.poligym.models.User;
import com.poligym.repository.ExercisesRepository;
import com.poligym.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ExercisesRepository exercisesRepository;


  private int id;

  @NotNull(message = "User id cannot be a null")
  private int users_id;

  @NotNull(message = "Exercise id cannot be a null")
  private int exercises_id;

  @NotNull(message = "Section cannot be a null")
  private String section;

  @NotNull(message = "Weight cannot be a null")
  private float weight;

  @NotNull(message = "series cannot be a null")
  private float series;

  @NotNull(message = "repetitions cannot be a null")
  private int repetitions;

  @NotNull(message = "training_validity cannot be a null")
  private Date trainning_validity;

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

    System.out.println("DTO: " + this.exercises_id);

    Trainning trainning = new ModelMapper().map(this, Trainning.class);

    System.out.println("ENTITY: " + trainning.getSeries());

    Exercises exercises = exercisesRepository.findById(this.exercises_id);

    System.out.println("EXERCICIO: " + exercises.getDescription());
    User users = userRepository.findById(this.users_id);

    trainning.setUsers(users);
    trainning.setExercises(exercises);

    return trainning;

  }

}
