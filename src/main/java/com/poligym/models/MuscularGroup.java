package com.poligym.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.poligym.dto.MuscularGroupDTO;

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
public class MuscularGroup extends EntityBase {

  private static final long serialVersionUID = 1980884079831325379L;

  @GeneratedValue
  @Id
  private int id;

  @Column(nullable = false, length = 255)
  private String description;

  public MuscularGroupDTO convertEntityToDTO() {
    return new ModelMapper().map(this, MuscularGroupDTO.class);
  }

}