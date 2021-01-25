package com.poligym.dto;

import javax.validation.constraints.NotNull;

import com.poligym.models.Section;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SectionDTO {
    private int id;

    @NotNull(message = "The description cannot be a null")
    private String description;

    @NotNull(message = "The daysRepetition cannot be a null")
    private int daysRepetition;

    public Section convertDTOToEntity() {
        return new ModelMapper().map(this, Section.class);
    }

}
