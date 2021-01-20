package com.poligym.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.poligym.dto.TrainningDTO;
import com.poligym.models.Exercises;
import com.poligym.models.Trainning;
import com.poligym.models.User;
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

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * SpringBoot RestController that creates all service end-points related to the trainning.
 * 
 * @author Pedro Vinicius
 * @since 18/01/2021
 */

@RestController
@RequestMapping(path = "/trainning")
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

    //Listar todos os treinos
    @GetMapping
    public ResponseEntity<List<TrainningDTO>> getAllTrains() {

        List<Trainning> trainnings = new ArrayList<>();
        trainnings = trainningRepository.findAll();

        List<TrainningDTO> trainningDTOs = new ArrayList<>();

        //Transformando Entidades em DTOs para retornar para o usuário
        for (Trainning trainning: trainnings) {
            TrainningDTO trainningDTO = new TrainningDTO();
            trainningDTO = trainning.convertEntityToDTO();
            trainningDTOs.add(trainningDTO);
        }

        return new ResponseEntity<>(trainningDTOs, HttpStatus.OK);
    }

    //Listar treino de usuario especifico
    @GetMapping(value = "/{user_id}")
    public ResponseEntity<Optional<List<TrainningDTO>>> getTrainByUser(@PathVariable int user_id) throws Exception {

      List<Trainning> trainnings = new ArrayList<>();
      trainnings = trainningRepository.findTrainningByUsersId(user_id);
      
      List<TrainningDTO> trainningDTOs = new ArrayList<>();

      if (trainnings.isEmpty()){
         new ResponseEntity<>("Train not found", HttpStatus.BAD_REQUEST);
      }

      for (Trainning trainning: trainnings) {
         TrainningDTO trainningDTO = new TrainningDTO();
         trainningDTO = trainning.convertEntityToDTO();
         trainningDTOs.add(trainningDTO);
     }

     return new ResponseEntity<Optional<List<TrainningDTO>>>(HttpStatus.OK);
    }


     @PostMapping
     public ResponseEntity<TrainningDTO> create(@Valid @RequestBody TrainningDTO dto) {
         Trainning trainning = dto.convertDTOToEntity();

        Trainning trainningToCreate = trainningRepository.save(trainning);

        TrainningDTO returnValue = trainningToCreate.convertEntityToDTO();

        return new ResponseEntity<TrainningDTO>(returnValue, HttpStatus.CREATED);
     }

     @PutMapping(value="/{id}")
     public ResponseEntity<TrainningDTO> upate(@PathVariable int id, @Validated @RequestBody TrainningDTO dto) throws Exception {
         
        //Busca se o treino passado pelo Id existe no banco
         Optional<Trainning> getBanco = trainningRepository.findById(id);

         if (!getBanco.isPresent()){
            new ResponseEntity<>("Train not found", HttpStatus.BAD_REQUEST);
         }

         //Precisamos verificar se o treino que o usuario esta tentando atualizar, contém os dados corretos no banco, como:
         // Verificar se o User existe
         Optional<User> getUser = userRepository.findById(dto.getUsers_id());

         if (!getUser.isPresent()) {
            new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
         }

         //Verificar se o exercicio existe
         Optional<Exercises> getExercises = exercisesRepository.findById(dto.getExercises_id());

         if (!getExercises.isPresent()) {
            new ResponseEntity<>("Exercise not found", HttpStatus.BAD_REQUEST);
         }

         Trainning trainningUpdate = new Trainning();
         //Caso sim, vamos converter o treino recebi pelo Body para Entidade
         Trainning trainningBody = dto.convertDTOToEntity();

         trainningUpdate.setUsers(trainningBody.getUsers());
         trainningUpdate.setExercises(trainningBody.getExercises());
         trainningUpdate.setSection(trainningBody.getSection());
         trainningUpdate.setSeries(trainningBody.getSeries());
         trainningUpdate.setRepetitions(trainningBody.getRepetitions());
         trainningUpdate.setWeight(trainningBody.getWeight());
         trainningUpdate.setTrainning_validity(trainningBody.getTrainning_validity());
         

         Trainning valueSave = trainningRepository.save(trainningUpdate);
         TrainningDTO returnValue = valueSave.convertEntityToDTO();

         return new ResponseEntity<>(returnValue, HttpStatus.OK);
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Optional<TrainningDTO>> delete(@PathVariable int id){
         try {
            trainningRepository.deleteById(id);
            return new ResponseEntity<Optional<TrainningDTO>>(HttpStatus.OK);
         } catch (Exception err) {
            return new ResponseEntity<Optional<TrainningDTO>>(HttpStatus.NOT_FOUND);
         }
     }

     
    
}
