package com.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitMq 配置
 * - exchange
 * - topic
 */
@Configuration
public class RabbitMqConfiguration {
    public static final String DEAD_LETTER_EXCHANGE = "delay.queue.dead_letter.exchange";
    public static final String DEAD_LETTER_QUEUE_ROUTING_KEY = "delay.queue.dead_letter.routing_key";
    public static final String DEAD_LETTER_QUEUE_NAME = "delay.queue.dead_letter.queue";
    public static final String DELAY_QUEUE_ROUTING_KEY = "delay.queue.business.queue.routing_key";


    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("123456");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        return factory;
    }


    public static final String DELAYED_QUEUE_NAME = "delay.queue.demo.delay.queue";
    public static final String DELAYED_EXCHANGE_NAME = "delay.queue.demo.delay.exchange";
    public static final String DELAYED_ROUTING_KEY = "delay.queue.demo.delay.routingkey";

    @Bean
    public Queue immediateQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotify(@Qualifier("immediateQueue") Queue queue,
                                 @Qualifier("customExchange") CustomExchange customExchange) {
        return BindingBuilder.bind(queue).to(customExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
