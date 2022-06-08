package com.java.coach.controller;

import com.java.coach.model.dto.ConceptDTO;
import com.java.coach.model.entity.Concept;
import com.java.coach.service.ConceptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
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

    @Operation(summary = "Get concept", description = "Get concept by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a specific concept",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Concept.class))}),
            @ApiResponse(responseCode = "404", description = "Concept not found by id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))})
    })
    @GetMapping(value = "/conceptos/{id}")
    public ResponseEntity<Concept> getConceptById(@PathVariable Integer id){
        return new ResponseEntity<>(conceptService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Create concept", description = "Save a new concept")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Concept successful created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))})
    })
    @PostMapping(value = "/crear")
    public ResponseEntity<Concept> createConcept(@RequestBody ConceptDTO conceptDTO){
        return new ResponseEntity<>(conceptService.saveConcepto(conceptDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update concept", description = "Update and save some concept")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Concept successful updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Concept not found by id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))})
    })
    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity<?> updateConcept(@PathVariable Integer id, @RequestBody ConceptDTO conceptDTO){
        conceptService.updateConcept(id, conceptDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete concept", description = "Delete concept by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Concept successful deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Concept not found by id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConceptDTO.class))})
    })
    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity deleteConcept(@PathVariable("id") Integer id){
        conceptService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
