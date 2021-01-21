package com.poligym.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.poligym.dto.UserDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.DynamicUpdate;
import org.modelmapper.ModelMapper;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Entity

public class User extends EntityBase {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
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
