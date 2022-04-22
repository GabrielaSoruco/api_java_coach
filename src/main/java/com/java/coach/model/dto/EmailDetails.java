package com.java.coach.model.dto;

import lombok.*;
import org.springframework.cache.annotation.AbstractCachingConfiguration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;
}