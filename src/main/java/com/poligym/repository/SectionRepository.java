package com.poligym.repository;

import com.poligym.models.Section;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    Section findById(int id);    
}
