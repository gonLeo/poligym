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
public class UserPresenceDTO {
    private int id;

    @NotNull(message = "The users_id cannot be a null")
    private int usersId;

    @NotNull(message = "The section_id cannot be a null")
    private int sectionId;

    public Section convertDTOToEntity() {
        return new ModelMapper().map(this, Section.class);
    }

}
