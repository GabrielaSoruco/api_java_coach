package com.java.coach.service;

import com.java.coach.exception.ResourceNotFoundException;
import com.java.coach.model.dto.ConceptDTO;
import com.java.coach.model.entity.Concept;
import com.java.coach.model.repository.ConceptRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConceptService {
    final ConceptRepository conceptRepository;

    ModelMapper modelMapper = new ModelMapper();

    public ConceptService(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
    }

    public List<ConceptDTO> findConceptos(){
        List<ConceptDTO> conceptsDTO = new ArrayList<>();
        List<Concept> concepts = conceptRepository.findAll();
        for (Concept concept: concepts) {
            ConceptDTO conceptDTO = modelMapper.map(concept, ConceptDTO.class);
            conceptsDTO.add(conceptDTO);
        }
        return conceptsDTO;
    }

    public Concept saveConcepto(ConceptDTO conceptoDTO){
        Concept concepto = modelMapper.map(conceptoDTO, Concept.class);
        return conceptRepository.save(concepto);
    }

    public Concept findById(Integer id){
        return conceptRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Concepto no encontrado con id: " +id));
    }

    public void updateConcept(Integer id, ConceptDTO conceptDTO){
        Concept conceptRepo = conceptRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe concepto con id: "+id));
        Concept concept = modelMapper.map(conceptDTO, Concept.class);
        concept.setIdConcepto(conceptRepo.getIdConcepto());
        conceptRepository.save(concept);
    }

    public void deleteById(Integer id){
        conceptRepository.deleteById(id);
    }

    public Concept findRandomConcept(){
        int max = (int) conceptRepository.count();
        int idRandom = (int) (Math.random() * max + 1);
            return conceptRepository.findById(idRandom).get();
    }
}
