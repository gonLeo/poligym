package com.poligym.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.poligym.dto.TrainningDTO;
import com.poligym.exception.ExercisesNotFoundException;
import com.poligym.exception.TrainningNotFoundException;
import com.poligym.exception.UserNotFoundException;
import com.poligym.models.Exercises;
import com.poligym.models.Trainning;
import com.poligym.repository.ExercisesRepository;
import com.poligym.repository.TrainningRepository;
import com.poligym.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * SpringBoot RestController that creates all service end-points related to the
 * trainning.
 * 
 * @author Pedro Vinicius
 * @since 18/01/2021
 */

@RestController
@Api(value = "Trainning")
@RequestMapping(path = "/v1")
public class TrainningController {

   @Autowired
   private TrainningRepository trainningRepository;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private ExercisesRepository exercisesRepository;

   @Autowired
   public TrainningController(TrainningRepository trainningRepository) {
      this.trainningRepository = trainningRepository;
   }

   @ApiOperation(value = "List all trainnings")
   @GetMapping(path = "/users/trainning")
   public ResponseEntity<List<TrainningDTO>> getAllTrains() throws TrainningNotFoundException {

      List<Trainning> trainnings = new ArrayList<>();
      trainnings = trainningRepository.findAll();

      if (trainnings.isEmpty()) {
         throw new TrainningNotFoundException("Trainig not found");
      }

      List<TrainningDTO> trainningDTOs = new ArrayList<>();

      // Transformando Entidades em DTOs para retornar para o usuário
      for (Trainning trainning : trainnings) {
         TrainningDTO trainningDTO = new TrainningDTO();
         trainningDTO = trainning.convertEntityToDTO();
         trainningDTOs.add(trainningDTO);
      }

      return new ResponseEntity<>(trainningDTOs, HttpStatus.OK);
   }

   @ApiOperation(value = "List a user trainning")
   @GetMapping(path = "/users/trainning/{usersId}")
   public ResponseEntity<List<TrainningDTO>> getTrainByUser(@PathVariable int usersId)
         throws TrainningNotFoundException {

      List<Trainning> trainnings = new ArrayList<>();
      trainnings = trainningRepository.findTrainningByUsersId(usersId);

      List<TrainningDTO> trainningDTOs = new ArrayList<>();

      if (trainnings.isEmpty()) {
         throw new TrainningNotFoundException("Training not found");
      }

      for (Trainning trainning : trainnings) {
         TrainningDTO trainningDTO = new TrainningDTO();
         trainningDTO = trainning.convertEntityToDTO();
         trainningDTOs.add(trainningDTO);
      }

      return new ResponseEntity<>(trainningDTOs, HttpStatus.OK);
   }

   @ApiOperation(value = "Create a trainning")

   @PostMapping(path = "/users/trainning")
   public ResponseEntity<TrainningDTO> create(@Valid @RequestBody TrainningDTO dto) {
      Trainning trainning = dto.convertDTOToEntity();

      Trainning trainningToCreate = trainningRepository.save(trainning);

      TrainningDTO returnValue = trainningToCreate.convertEntityToDTO();

      return new ResponseEntity<TrainningDTO>(returnValue, HttpStatus.CREATED);
   }

   @ApiOperation(value = "Change a trainning")
   @PutMapping(path = "/users/trainning/{id}")
   public ResponseEntity<TrainningDTO> upate(@PathVariable int id, @Validated @RequestBody TrainningDTO dto)
         throws TrainningNotFoundException, UserNotFoundException, ExercisesNotFoundException {

      // Busca se o treino passado pelo Id existe no banco
      Optional<Trainning> getBanco = trainningRepository.findById(id);

      if (!getBanco.isPresent()) {
         throw new TrainningNotFoundException("Training not found");
      }

      // Precisamos verificar se o treino que o usuario esta tentando atualizar,
      // contém os dados corretos no banco, como:
      // Verificar se o User existe
      if (userRepository.findById(dto.getUsersId()) == null) {
         throw new UserNotFoundException("User not found");
      }

      // Verificar se o exercicio existe
      Optional<Exercises> getExercises = exercisesRepository.findById(dto.getExercisesId());

      if (!getExercises.isPresent()) {
         throw new ExercisesNotFoundException("Exercise not found");
      }

      Trainning trainningUpdate = new Trainning();
      // Caso sim, vamos converter o treino recebi pelo Body para Entidade
      Trainning trainningBody = dto.convertDTOToEntity();

      trainningUpdate.setUsers(trainningBody.getUsers());
      trainningUpdate.setExercises(trainningBody.getExercises());
      trainningUpdate.setSection(trainningBody.getSection());
      trainningUpdate.setSeries(trainningBody.getSeries());
      trainningUpdate.setRepetitions(trainningBody.getRepetitions());
      trainningUpdate.setWeight(trainningBody.getWeight());
      trainningUpdate.setRest(trainningBody.getRest());

      Trainning valueSave = trainningRepository.save(trainningUpdate);
      TrainningDTO returnValue = valueSave.convertEntityToDTO();

      return new ResponseEntity<>(returnValue, HttpStatus.OK);
   }

   @ApiOperation(value = "Delete a trainning")
   @DeleteMapping(path = "/admin/trainning/{id}")
   public ResponseEntity<TrainningDTO> delete(@PathVariable int id) throws TrainningNotFoundException {
      // Busca se o treino passado pelo Id existe no banco
      Optional<Trainning> getTrainning = trainningRepository.findById(id);

      if (getTrainning.isPresent()) {
         trainningRepository.deleteById(id);
         return new ResponseEntity<TrainningDTO>(HttpStatus.OK);
      } else {
         return ResponseEntity.notFound().build();
      }

   }

}
