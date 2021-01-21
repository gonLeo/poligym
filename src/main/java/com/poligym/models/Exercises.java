
package com.poligym.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.poligym.dto.ExercisesDTO;

import org.hibernate.annotations.SQLDelete;
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
@SQLDelete(sql = "UPDATE exercises SET removed_at=now() WHERE id=?")
public class Exercises extends EntityBase {

  private static final long serialVersionUID = 1980887079833339379L;

  @GeneratedValue
  @Id
  private int id;

  @Column(nullable = true, length = 255)
  private String equipment;

  @Column(nullable = false, length = 255)
  private String description;

  @JoinColumn(name = "muscular_group_id")
  @OneToOne(targetEntity = MuscularGroup.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private MuscularGroup muscularGroup;

  @Override
  public String toString() {

    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("Id: ");
    stringBuffer.append(this.id);
    stringBuffer.append("\n Equipment: ");
    stringBuffer.append(this.equipment);
    stringBuffer.append("\n Muscle Group: ");
    stringBuffer.append(this.muscularGroup);
    stringBuffer.append("\n Description: ");
    stringBuffer.append(this.description);
    stringBuffer.append("\n created: ");
    stringBuffer.append(this.getCreatedAt());
    stringBuffer.append("\n Updated: ");
    stringBuffer.append(this.getRemovedAt());
    stringBuffer.append("\n Removed: ");
    stringBuffer.append(this.getRemovedAt());

    return stringBuffer.toString();
  }

  public ExercisesDTO convertEntityToDTO() {
    return new ModelMapper().map(this, ExercisesDTO.class);
  }

}

/*
 * "equipment" : "Barra fixa", "muscleGroup": "Biceps", "description":
 * "Tá Saindo da jaula o monstro"
 */