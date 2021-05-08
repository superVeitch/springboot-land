package com.redis.controller;

import com.redis.random.TestRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private TestRandom testRandom;

    /**
     * 测试发送延时消息
     *
     * @return
     */
    @PostMapping("/delayedSender")
    public String delayedSender(int ttl) {
        testRandom.test();
        return "ok";
    }

}
