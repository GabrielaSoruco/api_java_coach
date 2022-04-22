package com.java.coach.controller;

import com.java.coach.model.dto.QuestionDTO;
import com.java.coach.service.QuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Cuestionario")
@RestController
@RequestMapping("/cuestionario")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(value = "/all")
    public List<QuestionDTO> readQuestion(){
        return questionService.getQuestions();
    }

    @GetMapping(value = "/por/{nameConcept}")
    public QuestionDTO getQuestionByConcept(@PathVariable(name = "nameConcept") String nameConcept){
        return questionService.findQuestionsByConcept(nameConcept);
    }
}
