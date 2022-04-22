package com.java.coach.controller;

import com.java.coach.model.dto.ConceptDTO;
import com.java.coach.model.entity.Concept;
import com.java.coach.service.ConceptService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Conceptos")
@RestController
@RequestMapping(value = "/concepto")
public class ConceptController {

    @Autowired
    private ConceptService conceptService;

    @GetMapping(value = "/conceptos")
    public ResponseEntity<List<ConceptDTO>> readConcepts(){
        return ResponseEntity.ok(conceptService.findConceptos());
    }

    @PostMapping(value = "/crear")
    public ResponseEntity createConcept(@RequestBody ConceptDTO conceptDTO){
        conceptService.saveConcepto(conceptDTO);
        return ResponseEntity.ok("Successfully created");
    }

    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity updateConcept(@PathVariable Integer id, @RequestBody ConceptDTO conceptDTO){
        Optional<Concept> conceptoOptional = conceptService.findById(id);
        if(!conceptoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        conceptDTO.setIdConcepto(conceptoOptional.get().getIdConcepto());
        conceptService.saveConcepto(conceptDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity deleteConcept(@PathVariable("id") Integer id){
        Optional<Concept> concepto = conceptService.findById(id);
        if(!concepto.isPresent()){
            return ResponseEntity.unprocessableEntity().body("Concept not found");
        }
        conceptService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/enviar/consejo")
    public String sendConceptByEmail(){
        return "chequea tu correo";
    }

    @GetMapping(value = "/generar/reporte")
    public String generatePDF(){
        return "Generamos el pdf";
    }
}
