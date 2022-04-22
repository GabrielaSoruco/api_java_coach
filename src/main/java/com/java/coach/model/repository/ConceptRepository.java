package com.java.coach.model.repository;

import com.java.coach.model.entity.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, Integer> {

    @Query(value = "select c.nombreConcepto from Concept c where c.nombreConcepto like %:nameConcept%")
    String findNameConcept(@Param("nameConcept") String nameConcept);

    @Query(value = "select count(c.idConcepto) from Concept c")
    Integer getTotalConcepts();
}
