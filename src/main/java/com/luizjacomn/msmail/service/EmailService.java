package com.luizjacomn.msmail.service;

import com.luizjacomn.msmail.enumeration.EmailStatus;
import com.luizjacomn.msmail.model.Email;
import com.luizjacomn.msmail.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender mailSender;

    public Email sendEmail(Email model) {
        model.setSentAt(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(model.getEmailFrom());
            message.setTo(model.getEmailTo());
            message.setSubject(model.getSubject());
            message.setText(model.getText());

            mailSender.send(message);

            model.setEmailStatus(EmailStatus.SENT);
        } catch (MailException e) {
            e.printStackTrace();
            model.setEmailStatus(EmailStatus.ERROR);
        } finally {
            return emailRepository.save(model);
        }
    }
}
