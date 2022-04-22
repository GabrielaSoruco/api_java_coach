package com.java.coach.model.repository;

import com.java.coach.model.dto.QuestionDTO;
import com.java.coach.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "select q.contenidoPregunta from Question q join q.fkConcepto c where c.nombreConcepto like %:nameConcept%")
    List<String> findQuestionsByNameConcept(@Param("nameConcept") String nameConcept);

    @Query(value = "select count(idPregunta) from Question")
    Integer getTotalQuestions();
}
