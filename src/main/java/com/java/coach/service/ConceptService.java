package com.java.coach.service;

import com.java.coach.model.dto.ConceptDTO;
import com.java.coach.model.entity.Concept;
import com.java.coach.model.repository.ConceptRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConceptService {

    @Autowired
    ConceptRepository conceptRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<ConceptDTO> findConceptos(){
        List<ConceptDTO> conceptsDTO = new ArrayList<>();
        List<Concept> concepts = conceptRepository.findAll();
        for (Concept concept: concepts) {
            ConceptDTO conceptDTO = modelMapper.map(concept, ConceptDTO.class);
            conceptsDTO.add(conceptDTO);
        }
        return conceptsDTO;
    }

    public void saveConcepto(ConceptDTO conceptoDTO){
        Concept concepto = modelMapper.map(conceptoDTO, Concept.class);
        conceptRepository.save(concepto);
    }

    public Optional<Concept> findById(Integer id){
        return conceptRepository.findById(id);
    }

    public void deleteById(Integer id){
        conceptRepository.deleteById(id);
    }

}
