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
    public final static String DELAYED_EXCHANGE_NAME = "delay_exchange";
    public final static String DELAYED_QUEUE_NAME = "delayed.queue.test";
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


    @Bean("delayExchange")
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAYED_EXCHANGE_NAME);
    }

    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean("delayQueue")
    public Queue delayQueue() {
        Map<String, Object> argsDelay = new HashMap<>(2);
        argsDelay.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        argsDelay.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_ROUTING_KEY);
        return QueueBuilder.durable(DELAYED_QUEUE_NAME).withArguments(argsDelay).build();
    }

    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE_NAME);
    }

    @Bean
    public Binding delayBinding(@Qualifier("delayQueue") Queue queue,
                                @Qualifier("delayExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_ROUTING_KEY).noargs();
    }

    @Bean
    public Binding deadLetterBinding(@Qualifier("deadLetterQueue") Queue queue,
                                     @Qualifier("deadLetterExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_ROUTING_KEY).noargs();
    }
}
