package com.java.coach.controller;

import com.java.coach.model.dto.EmailDetails;
import com.java.coach.model.dto.QuestionDTO;
import com.java.coach.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cuestionario")
@Tag(name = "Cuestionario")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Operation(summary = "Obtener cuestionario", description = "Obtener todas las preguntas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de preguntas",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuestionDTO.class))})
    })
    @GetMapping(value = "/all")
    public List<QuestionDTO> readQuestion(){
        return questionService.getQuestions();
    }

    @Operation(summary = "Obtener pregunta/s por concepto", description = "Obtener aquellas preguntas según el concepto del path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de preguntas",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuestionDTO.class))})
    })
    @GetMapping(value = "/por/{nameConcept}")
    public QuestionDTO getQuestionByConcept(@PathVariable(name = "nameConcept") String nameConcept){
        return questionService.findQuestionsByConcept(nameConcept);
    }
}
