package com.EmailSender.EmailSender.controller;

import com.EmailSender.EmailSender.module.dto.request.EmailRequest;
import com.EmailSender.EmailSender.service.SmtpEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smtp-email")
public class SmtpEmailController {

    private final SmtpEmailService smtpEmailService;

    public SmtpEmailController(SmtpEmailService smtpEmailService) {
        this.smtpEmailService = smtpEmailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody EmailRequest emailRequest) {
        try {
            smtpEmailService.sendEmail(emailRequest.getEmail(), emailRequest.getContent(), emailRequest.getMessage());
            return ResponseEntity.ok("Email sent successfully via SMTP");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }
}

