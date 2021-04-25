package com.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.config.RabbitMqConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Consumer {

    @RabbitListener(queues = RabbitMqConfiguration.DELAYED_QUEUE_NAME)
    public void consumeGeneratorMsg(Message message, Channel channel,
                                    @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {
        String msg = new String(message.getBody());
        try{
            System.out.println(msg);
            SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            Date date = new Date();
            System.out.println("现在时间：" + sdf.format(date));
        } catch (Exception e){

        }finally {
            channel.basicAck(tag, false);
        }
    }
}
