package com.java.coach.service;

import com.java.coach.model.dto.EmailDTO;
import com.java.coach.model.entity.Concept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ConceptService conceptService;

    public String sendSimpleMail(EmailDTO emailDTO) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            Concept concept = conceptService.findRandomConcept();

            mailMessage.setFrom("java.coach.v1@gmail.com");
            mailMessage.setTo(emailDTO.getRecipient());
            mailMessage.setText(concept.getContenidoConcepto());
            mailMessage.setSubject(concept.getNombreConcepto());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
