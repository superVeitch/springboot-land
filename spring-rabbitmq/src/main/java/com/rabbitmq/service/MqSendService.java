package com.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.rabbitmq.config.RabbitMqConfiguration.DELAYED_EXCHANGE_NAME;
import static com.rabbitmq.config.RabbitMqConfiguration.DELAYED_ROUTING_KEY;

@Component
public class MqSendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void delayedMessage(int ttl) {
        String context = "test delay message: " + ttl;
        System.out.println("Send time: " + LocalDateTime.now() + "  Send: " + context);
        //延时时间6秒
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE_NAME, DELAYED_ROUTING_KEY, context, message -> {
            message.getMessageProperties().setDelay(ttl);
            return message;
        });
    }
}
