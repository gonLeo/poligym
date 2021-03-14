package com.poligym.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.poligym.dto.UsersDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.modelmapper.ModelMapper;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "Users")
@Entity
public class Users extends EntityBase {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registrationCode", nullable = false, unique = true)
    private String registrationCode;

    @Column(name = "registrationDate", nullable = false)
    private Date registrationDate;

    @Column(name = "medicalCertificateValidate", nullable = false)
    private Date medicalCertificateValidate;

    @Column(name = "admin", columnDefinition = "boolean default false", nullable = false)
    private boolean admin;

    public UsersDTO convertEntityToDTO() {
        return new ModelMapper().map(this, UsersDTO.class);
    }
}
