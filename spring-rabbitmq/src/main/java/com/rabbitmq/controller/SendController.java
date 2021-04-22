package com.rabbitmq.controller;

import com.rabbitmq.service.MqSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    @Autowired
    private MqSendService delayedSender;

    /**
     * 测试发送延时消息
     *
     * @return
     */
    @PostMapping("/delayedSender")
    public String delayedSender(int ttl) {
        delayedSender.delayedMessage(ttl);
        return "ok";
    }

}
