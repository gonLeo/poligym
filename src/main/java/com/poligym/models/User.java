package com.poligym.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data    
public class User{
    @GeneratedValue
    @Id
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "email")
    private String email;

    @Column(name = "registrationCode")
    private String registrationCode;

    @Column(name = "registrationDate")
    private Date registrationDate;

    @Column(name = "medicalCertificateValidate")
    private Date validityOfCertificate;        
    
    @Column(name="createdAt")        
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date createdAt;

    @Column(name="updatedAt")        
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name="removedAt")        
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date removedAt;

}
