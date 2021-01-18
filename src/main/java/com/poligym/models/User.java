package com.poligym.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.poligym.dto.UserDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User extends EntityBase {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registrationCode", nullable = false, unique = true)
    private String registrationCode;

    @Column(name = "registrationDate", nullable = false)
    private Date registrationDate;

    @Column(name = "medicalCertificateValidate", nullable = false)
    private Date medicalCertificateValidate;

    public UserDTO convertEntityToDTO() {
        return new ModelMapper().map(this, UserDTO.class);
    }
}
