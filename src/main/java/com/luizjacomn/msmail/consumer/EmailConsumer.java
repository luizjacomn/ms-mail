package com.luizjacomn.msmail.consumer;

import com.luizjacomn.msmail.dto.EmailDTO;
import com.luizjacomn.msmail.model.Email;
import com.luizjacomn.msmail.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        var model = new Email();
        BeanUtils.copyProperties(emailDTO, model);
        emailService.sendEmail(model);

        log.info("Email status: {}", model.getEmailStatus().name());
    }

}
