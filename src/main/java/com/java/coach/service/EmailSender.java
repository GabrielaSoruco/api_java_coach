package com.java.coach.service;

import com.java.coach.model.dto.EmailDetails;
import com.java.coach.model.repository.ConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ConceptRepository conceptRepository;

    @Value("${spring.mail.username}") private String sender;


    public String sendSimpleMail(EmailDetails details){
        int n = conceptRepository.getTotalConcepts();
        int numero = (int) (Math.random() * n) + 1;
        details.setMsgBody(conceptRepository.findById(numero).get().getContenidoConcepto());
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
