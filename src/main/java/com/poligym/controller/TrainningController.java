package com.poligym.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;
import javax.validation.Valid;

import com.poligym.dto.TrainningDTO;
import com.poligym.models.Trainning;
import com.poligym.repository.TrainningRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.expr.NewArray;

/**
 * SpringBoot RestController that creates all service end-points related to the trainning.
 * 
 * @author Pedro Vinicius
 * @since 18/01/2021
 */

@RestController
@RequestMapping(path = "/trainning")
public class TrainningController {

    private TrainningRepository trainningRepository;

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

        TrainningDTO dtoSaved = trainningToCreate.convertEntityToDTO();

        return new ResponseEntity<>(dtoSaved, HttpStatus.CREATED);
     }

     
    
}
