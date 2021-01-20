package com.poligym.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.poligym.dto.UserDTO;
import com.poligym.models.User;
import com.poligym.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController{
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserDetail(@PathVariable int id){                
        User userDetails = userRepository.findById(id);
        UserDTO userDto = new UserDTO();
        userDto = userDetails.convertEntityToDTO();
        
        return new ResponseEntity<>(userDto,HttpStatus.OK);        
    }


     @PostMapping
     public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
        
        User user = dto.convertDTOToEntity();
        User newUser = userRepository.save(user);

        UserDTO returnValue = newUser.convertEntityToDTO();

        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
     }     
}