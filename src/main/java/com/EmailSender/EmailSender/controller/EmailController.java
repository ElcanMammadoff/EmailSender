package com.EmailSender.EmailSender.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email-controller")
public class EmailController{

    @PostMapping("/send")
    public ResponseEntity<?> emailSender(@RequestBody EmailRequest){

    }

}
