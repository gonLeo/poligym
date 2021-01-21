package com.poligym.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.poligym.dto.SectionDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Section")
@Entity

public class Section extends EntityBase {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "daysRepetition", nullable = false)
    private String daysRepetition;

    public SectionDTO convertEntityToDTO() {
        return new ModelMapper().map(this, SectionDTO.class);
    }
}
