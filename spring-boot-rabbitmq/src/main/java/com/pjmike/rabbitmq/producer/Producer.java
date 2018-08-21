package com.pjmike.rabbitmq.producer;

import com.pjmike.rabbitmq.MQConfig.MqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author pjmike
 * @create 2018-08-19 17:37
 */
@Component
public class Producer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sender(String message) {
        System.out.println("生产者将要发送的消息: "+message);
        amqpTemplate.convertAndSend(MqConfig.QUEUE, message);
    }

    public void topicSender(String message) {
        //发送消息到topic_exchange交换器,路由键是topic.key1
        amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE, "com.key1",message+1);
        amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE,"topic.key2",message+2);
    }
}
