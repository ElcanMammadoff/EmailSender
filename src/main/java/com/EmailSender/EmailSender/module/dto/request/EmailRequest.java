package com.EmailSender.EmailSender.module.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    private String email;
    private String content;
    private String message;

}
