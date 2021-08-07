package com.luizjacomn.msmail.controller;

import com.luizjacomn.msmail.dto.EmailDTO;
import com.luizjacomn.msmail.model.Email;
import com.luizjacomn.msmail.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        Email model = new Email();
        BeanUtils.copyProperties(emailDTO, model);
        emailService.sendEmail(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }
}
