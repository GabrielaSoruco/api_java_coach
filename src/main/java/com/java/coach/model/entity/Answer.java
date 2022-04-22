package com.java.coach.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "RESPUESTAS")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESPUESTA")
    private Integer idRespuesta;

    @Column(name = "CONTENIDO_RESPUESTA")
    private String contenidoRespuesta;

    @Column(name = "ES_CORRECTA")
    private Boolean esCorrecta;

    @ManyToOne
    @JoinColumn(name = "FK_PREGUNTA")
    @JsonBackReference
    private Question fkPregunta;

}
