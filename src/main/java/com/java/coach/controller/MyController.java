package com.java.coach.controller;

import com.java.coach.model.dto.EmailDetails;
import com.java.coach.service.EmailSender;
import com.java.coach.service.ReportService;
import io.swagger.annotations.Api;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@Api(tags = "Otros")
@RestController
public class MyController {

    @Autowired
    ReportService reportService;

    @Autowired
    private EmailSender emailSender;

    @GetMapping(value = "/generar/reporte")
    public ResponseEntity<String> generateReport() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=javaCoachReport.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(reportService.exportReport());
    }

    @PostMapping("/enviar/consejo")
    public ResponseEntity<String> sendMail(@RequestBody EmailDetails details) {
        return ResponseEntity.ok(emailSender.sendSimpleMail(details));
    }


}
