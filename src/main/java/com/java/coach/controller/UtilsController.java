package com.java.coach.controller;

import com.java.coach.model.dto.EmailDTO;
import com.java.coach.model.dto.Report;
import com.java.coach.service.EmailSender;
import com.java.coach.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/utils")
@Tag(name = "Utilidades")
public class UtilsController {

    @Autowired
    ReportService reportService;
    @Autowired
    private EmailSender emailSender;

    @Operation(summary = "Download concept", description = "Download concept in pdf format")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concept successful downloaded",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Report.class))})
    })
    @GetMapping(value = "/generar/reporte", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<String> generateReport() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=javaCoachReport.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(reportService.exportReport());
    }

    @Operation(summary = "Send concept", description = "Send concept via email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Concept successful sended",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmailDTO.class))})
    })
    @PostMapping("/enviar/consejo")
    public ResponseEntity<String> sendMail(@RequestBody EmailDTO emailDTO) {
        return ResponseEntity.ok(emailSender.sendSimpleMail(emailDTO));
    }
}
