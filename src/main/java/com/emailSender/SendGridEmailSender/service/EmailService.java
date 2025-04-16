package com.emailSender.SendGridEmailSender.service;

import com.emailSender.SendGridEmailSender.EmailRepository;
import com.emailSender.SendGridEmailSender.model.entity.User;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    public void sendEmail(String to, String subject, String contentText) {
        if (to == null || to.isEmpty()) throw new IllegalArgumentException("Recipient email is required");
        if (subject == null) subject = "No Subject";
        if (contentText == null) contentText = "";

        Email from = new Email("youremail@email.com");
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", contentText);
        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Response response = null;
        try {
            response = sg.api(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Status Code: " + response.getStatusCode());
    }


    public String findSecurityCodeFromEmail(String email){
        User user=emailRepository.findByEmail(email).get();
       return user.getSecurityCode();
    }
}
