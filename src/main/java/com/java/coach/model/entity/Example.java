package com.java.coach.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EJEMPLOS")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EJEMPLO")
    private Integer idEjemplo;

    @Column(name = "DESCRIPCION_EJEMPLO")
    private String descripcionEjemplo;

    @Column(name = "CONTENIDO_EJEMPLO")
    private String contenidoEjemplo;

    @ManyToOne
    @JoinColumn(name = "FK_CONCEPTO")
    @JsonBackReference
    private Concept fkConcepto;
}
