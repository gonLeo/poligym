package com.poligym.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.poligym.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {
    private int id;

    @NotNull(message = "The name cannot be a null")
    private String name;

    @NotNull(message = "the email cannot be a null")
    private String email;

    @NotNull(message = "the password cannot be a null")
    private String password;

    @NotNull(message = "the registrationCode cannot be a null")
    private String registrationCode;

    @NotNull(message = "the registrationDate cannot be a null")
    private Date registrationDate;

    @NotNull(message = "the medicalCertificateValidate cannot be a null")
    private Date medicalCertificateValidate;

    public User convertDTOToEntity() {
        return new ModelMapper().map(this, User.class);
    }

}
