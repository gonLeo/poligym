package com.poligym.controller;

import java.util.List;
import java.util.Optional;

import com.poligym.dto.ExercisesDTO;
import com.poligym.models.Exercises;
import com.poligym.repository.ExercisesRepository;
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

@RestController
@RequestMapping("/muscular-group")
public class MuscularGroupController {

  @Autowired
  MuscularGroupRepository muscularGroupRepository;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Exercises>> show(@PathVariable Integer id) {
    Optional<MuscularGroup> muscularGroup;
    try {
      muscularGroup = muscularGroupRepository.findById(id);
      return new ResponseEntity<Optional<Exercises>>(exercises, HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<Optional<Exercises>>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping()
  public List<Exercises> index() {
    List<Exercises> exercises = muscularGroupRepository.findAll();
    return exercises;
  }

  @PostMapping()
  public ResponseEntity<Exercises> save(@RequestBody ExercisesDTO exercisesDTO) {

    Exercises exercises = exercisesDTO.convertEntityToExercises();
    muscularGroupRepository.save(exercises);

    return new ResponseEntity<>(exercises, HttpStatus.OK);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Exercises> update(@RequestBody ExercisesDTO exercisesDTO, @PathVariable Integer id) {

    return muscularGroupRepository.findById(id).map(exercises -> {
      Exercises updated = exercisesDTO.convertEntityToExercises();
      exercises.setEquipment(updated.getEquipment());
      exercises.setMuscularGroup(updated.getMuscularGroup());
      exercises.setDescription(updated.getDescription());

      Exercises exerciseUpdated = muscularGroupRepository.save(exercises);
      return ResponseEntity.status(HttpStatus.OK).body(exerciseUpdated);
    }).orElse(ResponseEntity.notFound().build());
  };

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Optional<Exercises>> update(@PathVariable Integer id) {
    try {
      muscularGroupRepository.deleteById(id);
      return new ResponseEntity<Optional<Exercises>>(HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<Optional<Exercises>>(HttpStatus.NOT_FOUND);
    }
  }

}
