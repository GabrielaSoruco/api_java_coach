package com.java.coach.controller;

import com.java.coach.model.dto.ConceptDTO;
import com.java.coach.model.dto.EmailDetails;
import com.java.coach.model.entity.Concept;
import com.java.coach.service.ConceptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/concepto")
@Tag(name = "Conceptos")
public class ConceptController {

    private final ConceptService conceptService;

    public ConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @Operation(summary = "Get concepts", description = "Get a list of Concepts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of concepts",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))})
    })
    @GetMapping(value = "/conceptos")
    public ResponseEntity<List<ConceptDTO>> readConcepts(){
        return ResponseEntity.ok(conceptService.findConceptos());
    }

    @Operation(summary = "Create concept", description = "Save a new concept")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concept successful saved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))})
    })
    @PostMapping(value = "/crear")
    public ResponseEntity createConcept(@RequestBody ConceptDTO conceptDTO){
        conceptService.saveConcepto(conceptDTO);
        return ResponseEntity.ok("Successfully created");
    }

    @Operation(summary = "Update concept", description = "Update and save some concept")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Concept successful updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))}),
            @ApiResponse(responseCode = "422", description = "Concept not found by id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))})
    })
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

    @Operation(summary = "Delete concept", description = "Delete concept by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Concept successful deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))}),
            @ApiResponse(responseCode = "422", description = "Concept not found by id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))})
    })
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity deleteConcept(@PathVariable("id") Integer id){
        Optional<Concept> concepto = conceptService.findById(id);
        if(!concepto.isPresent()){
            return ResponseEntity.unprocessableEntity().body("Concept not found");
        }
        conceptService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
