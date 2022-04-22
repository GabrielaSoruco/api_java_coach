package com.java.coach.model.repository;

import com.java.coach.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query(value = "select a.contenidoRespuesta from Answer a join a.fkPregunta q join q.fkConcepto c where c.nombreConcepto like %:nameConcept%")
    List<String> findAnswerByNameConcept(@Param("nameConcept") String nameConcept);

    @Query(value = "select count(idRespuesta) from Answer")
    Integer getTotalAnswers();
}
