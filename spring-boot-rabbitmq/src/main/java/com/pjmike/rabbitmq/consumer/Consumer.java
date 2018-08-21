package com.pjmike.rabbitmq.consumer;

import com.pjmike.rabbitmq.MQConfig.MqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author pjmike
 * @create 2018-08-19 17:38
 */
@Component
public class Consumer {
    @RabbitListener(queues = MqConfig.QUEUE)
    public void receive(String message) {
        System.out.println("收到的信息: "+message);
    }

    @RabbitListener(queues = MqConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        System.out.println("topic queue1 receive message: "+message);
    }

    @RabbitListener(queues = MqConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        System.out.println("topic queue2 receive message: "+message);
    }
}
