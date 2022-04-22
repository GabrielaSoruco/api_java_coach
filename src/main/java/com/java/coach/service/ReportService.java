package com.java.coach.service;

import com.java.coach.model.dto.Report;
import com.java.coach.model.repository.AnswerRepository;
import com.java.coach.model.repository.ConceptRepository;
import com.java.coach.model.repository.ExampleRepository;
import com.java.coach.model.repository.QuestionRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ReportService {
    final ConceptRepository conceptRepository;

    final QuestionRepository questionRepository;

    final AnswerRepository answerRepository;

    final ExampleRepository exampleRepository;

    public ReportService(ConceptRepository conceptRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, ExampleRepository exampleRepository) {
        this.conceptRepository = conceptRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.exampleRepository = exampleRepository;
    }

    public Report generateReport(){
        Integer conceptos = conceptRepository.getTotalConcepts();
        Integer ejemplos = exampleRepository.getTotalExamples();
        Integer preguntas = questionRepository.getTotalQuestions();
        Integer respuestas = answerRepository.getTotalAnswers();
        return new Report(conceptos, ejemplos, preguntas, respuestas);
    }

    public String exportReport() throws FileNotFoundException, JRException {
        List<Report> reports = Arrays.asList(generateReport());
        File file = ResourceUtils.getFile("classpath:javaCoach_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("createdBy", "Gabriela Soruco");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\reportes" + "\\javaCoach.pdf");
        return "report generated in path: " + "C:\\reportes";

    }

}
