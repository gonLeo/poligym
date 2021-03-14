package com.poligym.controller;

import javax.validation.Valid;

import com.poligym.dto.UsersDTO;
import com.poligym.models.Users;
import com.poligym.repository.UserRepository;
import com.poligym.utils.security.BcryptUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "User")
@RequestMapping(path = "/v1")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @ApiOperation(value = "Create a user")    
    @PostMapping(value = "/create")
    public ResponseEntity<UsersDTO> create(@Valid @RequestBody UsersDTO dto) {

        Users user = dto.convertDTOToEntity();

        String password = BcryptUtils.getHash(user.getPassword());
        user.setPassword(password);
        
        Users newUser = userRepository.save(user);

        UsersDTO returnValue = newUser.convertEntityToDTO();

        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @ApiOperation(value = "List a user")    
    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UsersDTO> getUserDetail(@PathVariable int id) throws Exception {

        if (userRepository.findById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            Users userDetails = userRepository.findById(id);
            UsersDTO userDto = new UsersDTO();
            userDto = userDetails.convertEntityToDTO();

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }

    }
    
    @ApiOperation(value = "Change a user")    
    @PutMapping(path = "/users/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable int id, @RequestBody UsersDTO dto) {

        if (userRepository.findById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            Users user = userRepository.findById(id);
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setRegistrationCode(dto.getRegistrationCode());
            user.setRegistrationDate(dto.getRegistrationDate());
            user.setMedicalCertificateValidate(dto.getMedicalCertificateValidate());

            UsersDTO userUpdated = userRepository.save(user).convertEntityToDTO();

            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }

    }
    @ApiOperation(value = "Delete a user")        

    @DeleteMapping(path = "/admin/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Users> updateUser(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<Users>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
    }

}