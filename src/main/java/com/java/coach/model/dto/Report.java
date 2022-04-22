package com.java.coach.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Report {
    private Integer conceptos;
    private Integer ejemplos;
    private Integer preguntas;
    private Integer respuestas;
}
