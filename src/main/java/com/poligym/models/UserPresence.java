
package com.poligym.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poligym.dto.ExercisesDTO;
import com.poligym.dto.UserPresenceDTO;

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
public class UserPresence extends EntityBase {

    private static final long serialVersionUID = 1980887079833339379L;

    @GeneratedValue
    @Id
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "users_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private User users;

    public UserPresenceDTO convertEntityToDTO() {
        return new ModelMapper().map(this, UserPresenceDTO.class);
    }

}
