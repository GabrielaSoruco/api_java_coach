package com.java.coach.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO implements Serializable{
    private String concepto;
    private List<String> preguntas;
    private List<String> respuestas;
}
