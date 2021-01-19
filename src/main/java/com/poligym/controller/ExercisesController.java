package com.poligym.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.poligym.dto.ExercisesDTO;
import com.poligym.models.Exercises;
import com.poligym.repository.ExercisesRepository;

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
@RequestMapping("/exercises")
public class ExercisesController {

  @Autowired
  ExercisesRepository exercisesRepository;

  @GetMapping(value = "/{id}")
  public Exercises show(@PathVariable Integer id) {
    Exercises exercises = exercisesRepository.getOne(id);
    return exercises;
  }

  @GetMapping()
  public List<Exercises> index() {
    List<Exercises> exercises = exercisesRepository.findAll();
    return exercises;
  }

  @PostMapping()
  public ResponseEntity<Exercises> save(@RequestBody ExercisesDTO exercisesDTO) {

    Exercises exercises = exercisesDTO.convertEntityToExercises();

    System.out.println("\n");
    System.out.println(exercises.toString());
    System.out.println("\n");

    exercisesRepository.save(exercises);

    return new ResponseEntity<>(exercises, HttpStatus.OK);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Exercises> update(@RequestBody ExercisesDTO exercisesDTO, @PathVariable Integer id) {
    Exercises exercises = exercisesRepository.getOne(id);
    Exercises updated = exercisesDTO.convertEntityToExercises();
    exercises.setEquipment(updated.getEquipment());
    exercises.setMuscleGroup(updated.getMuscleGroup());
    exercises.setDescription(updated.getDescription());

    exercisesRepository.save(exercises);
    return ResponseEntity.status(HttpStatus.OK).body(exercises);
  };

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Optional<Exercises>> update(@PathVariable Integer id) {
    try {
      // Exercises exercisesDelete = exercisesRepository.getOne(id);
      exercisesRepository.deleteById(id);
      return new ResponseEntity<Optional<Exercises>>(HttpStatus.OK);
    } catch (NoSuchElementException err) {
      return new ResponseEntity<Optional<Exercises>>(HttpStatus.NOT_FOUND);
    }
  }

}
