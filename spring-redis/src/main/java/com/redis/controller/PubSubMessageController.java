package com.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PubSubMessageController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/pub")
    public String delayedSender(String msg) {
        redisTemplate.convertAndSend("veitch", msg);
        return "ok";
    }
}
