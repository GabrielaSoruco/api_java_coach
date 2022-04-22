package com.java.coach.service;

import com.java.coach.model.dto.QuestionDTO;
import com.java.coach.model.entity.Question;
import com.java.coach.model.repository.AnswerRepository;
import com.java.coach.model.repository.ConceptRepository;
import com.java.coach.model.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    final ConceptRepository conceptRepository;

    final QuestionRepository questionRepository;

    final AnswerRepository answerRepository;

    ModelMapper modelMapper = new ModelMapper();

    public QuestionService(ConceptRepository conceptRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.conceptRepository = conceptRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public List<QuestionDTO> getQuestions(){
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionsDTO = new ArrayList<>();
        for (Question question: questions) {
            QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
            questionsDTO.add(questionDTO);
        }
        return questionsDTO;
    }

    public QuestionDTO findQuestionsByConcept(String nameConcept){
        String concepto = conceptRepository.findNameConcept(nameConcept);
        List<String> preguntas = questionRepository.findQuestionsByNameConcept(nameConcept);
        List<String> respuestas = answerRepository.findAnswerByNameConcept(nameConcept);
        QuestionDTO questionDTO = new QuestionDTO(concepto, preguntas, respuestas);
        return questionDTO;
    }

}
