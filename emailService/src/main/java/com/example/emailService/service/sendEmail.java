package com.example.emailService.service;

import com.example.emailService.config.RabbitMqEmailQueue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class sendEmail {
    @RabbitListener(queues = RabbitMqEmailQueue.QUEUE)
    public void setMessage(String email){
//            FilterRabbit(token);

    }
}
