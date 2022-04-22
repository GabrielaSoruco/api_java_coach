package com.java.coach.model.repository;

import com.java.coach.model.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExampleRepository extends JpaRepository<Example, Integer> {

    @Query(value = "select e.contenidoEjemplo from Example e join e.fkConcepto c where c.nombreConcepto like %:nameConcept%")
    List<String> findExampleByNameConcept(@Param("nameConcept") String nameConcept);

    @Query(value = "select count(idEjemplo) from Example")
    Integer getTotalExamples();
}
