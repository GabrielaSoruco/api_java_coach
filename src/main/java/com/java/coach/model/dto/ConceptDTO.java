package com.java.coach.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConceptDTO implements Serializable {

    private int idConcepto;
    private String nombreConcepto;
    private String contenidoConcepto;
}
