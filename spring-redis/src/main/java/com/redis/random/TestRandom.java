package com.redis.random;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class TestRandom {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String KEY = "redis-key-17502142159";

    private static final Executor executor = Executors.newFixedThreadPool(2500);

    public void test() {
        sadd();
        System.out.println(redisTemplate.boundSetOps(KEY).size());
        for (int i = 0; i < 50000; i++) {
            executor.execute(() -> {
                List<Object> value = redisTemplate.boundHashOps(KEY).values();
                if (Objects.isNull(value)){
                    System.out.println("test is null!!!" );
                }
            });
        }
    }

    private void sadd(){
        for (int i=0; i< 100; i++){
            // 构建缓存session数据
            ClientSessionInfo clientSessionInfo = new ClientSessionInfo();
            clientSessionInfo.setClientId("/java/17502142160/1234567"+i);
            clientSessionInfo.setCleanSession(false);
            // 用于标记客户端是离线还是在线
            clientSessionInfo.setConnStatus("ON");
            // 缓存时的时间认为是操作时间
            clientSessionInfo.setConnTime(System.currentTimeMillis());
            redisTemplate.boundHashOps(KEY).put(clientSessionInfo.getClientId(), "");
        }

    }
}
