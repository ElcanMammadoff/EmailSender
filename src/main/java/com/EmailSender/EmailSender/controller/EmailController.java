package com.EmailSender.EmailSender.controller;

import com.EmailSender.EmailSender.module.dto.request.EmailRequest;
import com.EmailSender.EmailSender.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/email-controller")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody EmailRequest emailRequest) {
        try {
            var response = emailService.sendEmail(
                    emailRequest.getEmail(),
                    emailRequest.getContent(),
                    emailRequest.getMessage()
            );

            // Show full result
            return ResponseEntity
                    .status(response.getStatusCode())
                    .body("Status: " + response.getStatusCode() + "\nBody: " + response.getBody());

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("❌ Error: " + e.getMessage());
        }
    }
}

