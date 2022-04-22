package com.java.coach.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "CONCEPTOS")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Concept{

    @Id
    @Column(name = "ID_CONCEPTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConcepto;

    @Column(name = "NOMBRE_CONCEPTO", nullable = false)
    private String nombreConcepto;

    @Lob
    @Column(name = "CONTENIDO_CONCEPTO", nullable = false, length = 512)
    private String contenidoConcepto;

    @OneToMany(mappedBy = "fkConcepto")
    @JsonManagedReference
    private List<Question> questions;

    @OneToMany(mappedBy = "fkConcepto")
    @JsonManagedReference
    private List<Example> examples;
}
