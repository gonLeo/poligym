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
    private UsersRepository UserRepository;

    @Autowired
    private ExercisesRepository exercisesRepository;

    @Autowired
    public TrainningController(TrainningRepository trainningRepository) {
        this.trainningRepository = trainningRepository;
    }

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

    /**
     * Method that creates trainning in the database.
     * 
     * @author Pedro Vinicius @since 18/01/2021
     * 
     * @param apiVersion - API version at the moment @param apiKey - API Key to
     * access the routes @param dto, where: - id - trainning id; - user_id -
     * identification number of a user in the system; - exercise_id - identification
     * number of a exercise in the system; - weight – weight of exercise - series -
     * how much series contains in a exercise - repetitions - how much repetitions
     * contains in a exercise - training_validity - train validate format
     * YYYY-MM-DDThh:mm:ss.sssZ in the Local time zone;
     * 
     * @param result - Bind result
     * 
     * @return ResponseEntity with a <code>Response<TrainningDTO></code> object and
     * the HTTP status
     * 
     * HTTP Status:
     * 
     * 201 - Created: Everything worked as expected. 400 - Bad Request: The request
     * was unacceptable, often due to missing a required parameter. 404 - Not Found:
     * The requested resource doesn't exist. 409 - Conflict: The request conflicts
     * with another request (perhaps due to using the same idempotent key). 422 –
     * Unprocessable Entity: if any of the fields are not parsable or the start date
     * is greater than end date. 429 - Too Many Requests: Too many requests hit the
     * API too quickly. We recommend an exponential back-off of your requests. 500,
     * 502, 503, 504 - Server Errors: something went wrong on API end (These are
     * rare).
     * 
     * @throws
     */

     @PostMapping
     public ResponseEntity<TrainningDTO> create(@Valid @RequestBody TrainningDTO dto) {
        
        Trainning trainning = dto.convertDTOToEntity();
        Trainning trainningToCreate = trainningRepository.save(trainning);

        TrainningDTO returnValue = trainningToCreate.convertEntityToDTO();

        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
     }

     @PutMapping(value="/{id}")
     public ResponseEntity<TrainningDTO> upate(@PathVariable int id, @RequestBody TrainningDTO dto) throws Exception {
         //TODO: process PUT request
         
        //Busca se o treino passado pelo Id existe no banco
         Optional<Trainning> getBanco = trainningRepository.findById(id);

         if (!getBanco.isPresent()){
            new ResponseEntity<>("Train not found", HttpStatus.BAD_REQUEST);
         }

         //Caso sim, vamos converter o treino recebi pelo Body para Entidade
         Trainning trainning = dto.convertDTOToEntity();

         //Precisamos verificar se o treino que o usuario esta tentando atualizar, contém os dados corretos no banco, como:
         // Verificar se o User existe

         Optional<User> getUser = usersRepository.findById(dto.getUsers());

         if (!getUser.isPresent()) {
            new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
         }

         //Verificar se o exercicio existe
         Optional<Exercises> getExercises = exercisesRepository.findById(dto.getExercises());

         if (!getExercises.isPresent()) {
            new ResponseEntity<>("Exercise not found", HttpStatus.BAD_REQUEST);
         }

         Trainning trainningUpdate = new Trainning();
         Trainning trainningBody = dto.convertDTOToEntity();

         trainningUpdate.setUsers(trainningBody.getUsers());
         trainningUpdate.setExercises(trainningBody.getExercises());
         trainningUpdate.setSection(trainningBody.getSection());
         trainningUpdate.setSeries(trainningBody.getSeries());
         trainningUpdate.setRepetitions(trainningBody.getRepetitions());
         trainningUpdate.setWeight(trainningBody.getWeight());
         trainningUpdate.setTraining_validity(trainningBody.getTraining_validity());
         

         Trainning valueSave = trainningRepository.save(trainningUpdate);
         TrainningDTO returnValue = valueSave.convertEntityToDTO();

         return new ResponseEntity<>(returnValue, HttpStatus.OK);
     }

     
    
}
