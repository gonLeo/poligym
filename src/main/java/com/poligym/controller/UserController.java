package com.poligym.controller;

import javax.validation.Valid;

import com.poligym.dto.UserDTO;
import com.poligym.models.User;
import com.poligym.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/users")
@Api(value = "User")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @ApiOperation(value = "Create a user")
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {

        User user = dto.convertDTOToEntity();
        User newUser = userRepository.save(user);

        UserDTO returnValue = newUser.convertEntityToDTO();

        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @ApiOperation(value = "List a user")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserDetail(@PathVariable int id) throws Exception {

        if (userRepository.findById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            User userDetails = userRepository.findById(id);
            UserDTO userDto = new UserDTO();
            userDto = userDetails.convertEntityToDTO();

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }

    }
    
    @ApiOperation(value = "Change a user")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO dto) {

        if (userRepository.findById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            User user = userRepository.findById(id);
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setRegistrationCode(dto.getRegistrationCode());
            user.setRegistrationDate(dto.getRegistrationDate());
            user.setMedicalCertificateValidate(dto.getMedicalCertificateValidate());

            UserDTO userUpdated = userRepository.save(user).convertEntityToDTO();

            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }

    }
    @ApiOperation(value = "Delete a user")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<User>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

}