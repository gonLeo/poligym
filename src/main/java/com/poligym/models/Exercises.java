
package com.poligym.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.poligym.dto.ExercisesDTO;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exercises extends EntityBase {

  private static final long serialVersionUID = 1980887079833339379L;

  @GeneratedValue
  @Id
  private int id;

  @Column(nullable = false, length = 255)
  private String equipment;

  @Column(unique = true, nullable = false, length = 255)
  private String muscleGroup;

  @Column(nullable = false, length = 255)
  private String description;

  @Override
  public String toString() {

    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("Id: ");
    stringBuffer.append(this.id);
    stringBuffer.append("\n Equipment: ");
    stringBuffer.append(this.equipment);
    stringBuffer.append("\n Muscle Group: ");
    stringBuffer.append(this.muscleGroup);
    stringBuffer.append("\n Description: ");
    stringBuffer.append(this.description);

    return stringBuffer.toString();
  }

  public ExercisesDTO convertEntityToDTO() {
    return new ModelMapper().map(this, ExercisesDTO.class);
  }

}
