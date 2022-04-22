package com.java.coach.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PREGUNTAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PREGUNTA")
    private Integer idPregunta;

    @Column(name = "CONTENIDO_PREGUNTA")
    private String contenidoPregunta;

    @ManyToOne
    @JoinColumn(name = "FK_CONCEPTO")
    @JsonBackReference
    private Concept fkConcepto;

    @OneToMany(mappedBy = "fkPregunta")
    @JsonManagedReference
    private List<Answer> respuestas;
}
