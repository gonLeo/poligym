package com.poligym.controller;

import java.util.ArrayList;
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
@RequestMapping("/v1")
public class MuscularGroupController {

  @Autowired
  MuscularGroupRepository muscularGroupRepository;

  @GetMapping(path = "/users/muscular-group/{id}")
  public ResponseEntity<MuscularGroupDTO> show(@PathVariable Integer id) {
    return muscularGroupRepository.findById(id).map(muscularGroup -> {
      MuscularGroupDTO returnValue = muscularGroup.convertEntityToDTO();
      return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping(path = "/users/muscular-group")
  public List<MuscularGroupDTO> index() {
    List<MuscularGroup> muscularGroups = muscularGroupRepository.findAll().stream()
        .filter(muscularGroupData -> muscularGroupData.getRemovedAt() == null).collect(Collectors.toList());

    List<MuscularGroupDTO> muscularGroupDTOs = new ArrayList<>();

    for (MuscularGroup muscularGroup : muscularGroups) {
      MuscularGroupDTO muscularGroupDTO = new MuscularGroupDTO();
      muscularGroupDTO = muscularGroup.convertEntityToDTO();
      muscularGroupDTOs.add(muscularGroupDTO);
    }

    return muscularGroupDTOs;
  }

  @GetMapping(path = "/users/muscular-group/all")
  public List<MuscularGroupDTO> indexAll() {
    List<MuscularGroup> muscularGroups = muscularGroupRepository.findAll();

    List<MuscularGroupDTO> muscularGroupDTOs = new ArrayList<>();

    for (MuscularGroup muscularGroup : muscularGroups) {
      MuscularGroupDTO muscularGroupDTO = new MuscularGroupDTO();
      muscularGroupDTO = muscularGroup.convertEntityToDTO();
      muscularGroupDTOs.add(muscularGroupDTO);
    }

    return muscularGroupDTOs;
  }

  @PostMapping(path = "/users/muscular-group")
  public ResponseEntity<MuscularGroupDTO> save(@RequestBody MuscularGroupDTO muscularGroupDTO) {

    try {
      MuscularGroup muscularGroup = muscularGroupDTO.convertDTOToEntity();
      MuscularGroup returnValue = muscularGroupRepository.save(muscularGroup);

      return new ResponseEntity<>(returnValue.convertEntityToDTO(), HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<MuscularGroupDTO>(HttpStatus.BAD_REQUEST);
    }

  }

  @PutMapping(path = "/users/muscular-group/{id}")
  public ResponseEntity<MuscularGroupDTO> update(@RequestBody MuscularGroupDTO muscularGroupDTO,
      @PathVariable Integer id) {

    return muscularGroupRepository.findById(id).map(muscularGroup -> {
      MuscularGroup updated = muscularGroupDTO.convertDTOToEntity();
      muscularGroup.setDescription(updated.getDescription());

      MuscularGroup muscularGroupUpdated = muscularGroupRepository.save(muscularGroup);
      MuscularGroupDTO returnValue = muscularGroupUpdated.convertEntityToDTO();
      return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }).orElse(ResponseEntity.notFound().build());
  };

  @DeleteMapping(path = "/admin/muscular-group/{id}")
  public ResponseEntity<Optional<MuscularGroup>> update(@PathVariable Integer id) {
    try {
      muscularGroupRepository.deleteById(id);
      return new ResponseEntity<Optional<MuscularGroup>>(HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<Optional<MuscularGroup>>(HttpStatus.NOT_FOUND);
    }
  }

}
