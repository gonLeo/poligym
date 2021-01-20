package com.poligym.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.poligym.dto.MuscularGroupDTO;
import com.poligym.models.MuscularGroup;
import com.poligym.repository.MuscularGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/muscular-group")
public class MuscularGroupController {

  @Autowired
  MuscularGroupRepository muscularGroupRepository;

  @GetMapping(value = "/{id}")
  public ResponseEntity<MuscularGroup> show(@PathVariable Integer id) {
    return muscularGroupRepository.findById(id).map(muscularGroup -> {
      return ResponseEntity.status(HttpStatus.OK).body(muscularGroup);
    }).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping()
  public List<MuscularGroup> index() {
    List<MuscularGroup> exercises = muscularGroupRepository.findAll().stream().filter(s -> s.getRemovedAt() == null)
        .collect(Collectors.toList());

    return exercises;
  }

  @GetMapping(value = "/all")
  public List<MuscularGroup> indexAll() {
    List<MuscularGroup> exercises = muscularGroupRepository.findAll();
    return exercises;
  }

  @PostMapping()
  public ResponseEntity<MuscularGroup> save(@RequestBody MuscularGroupDTO muscularGroupDTO) {

    MuscularGroup muscularGroup = muscularGroupDTO.convertEntityToExercises();
    muscularGroupRepository.save(muscularGroup);

    return new ResponseEntity<>(muscularGroup, HttpStatus.OK);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<MuscularGroup> update(@RequestBody MuscularGroupDTO muscularGroupDTO,
      @PathVariable Integer id) {

    return muscularGroupRepository.findById(id).map(muscularGroup -> {
      MuscularGroup updated = muscularGroupDTO.convertEntityToExercises();
      muscularGroup.setDescription(updated.getDescription());

      MuscularGroup muscularGroupUpdated = muscularGroupRepository.save(muscularGroup);
      return ResponseEntity.status(HttpStatus.OK).body(muscularGroupUpdated);
    }).orElse(ResponseEntity.notFound().build());
  };

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Optional<MuscularGroup>> update(@PathVariable Integer id) {
    try {
      muscularGroupRepository.deleteById(id);
      return new ResponseEntity<Optional<MuscularGroup>>(HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<Optional<MuscularGroup>>(HttpStatus.NOT_FOUND);
    }
  }

}
