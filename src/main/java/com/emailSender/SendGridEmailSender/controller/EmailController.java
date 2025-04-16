package com.emailSender.SendGridEmailSender.controller;

import com.emailSender.SendGridEmailSender.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    @Transactional
    public ResponseEntity<String> send(@RequestParam String recipentEmail) {
        try {
//            String securityCode = emailService.findSecurityCodeFromEmail(recipentEmail);
//            if (securityCode != null) {
                emailService.sendEmail(recipentEmail, "SendGrid Email", "this is email with sendGrid");
                return ResponseEntity.ok("Email sent successfully");
//            } else {
//                return ResponseEntity.status(404).body("No security code found for this email");
//            }
        } catch (Exception e) {
            e.printStackTrace(); // Or use logger
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }

}

