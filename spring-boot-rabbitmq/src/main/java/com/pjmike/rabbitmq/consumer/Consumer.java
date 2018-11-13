package com.pjmike.rabbitmq.consumer;

import com.pjmike.rabbitmq.MQConfig.RabbitConfig;
import com.pjmike.rabbitmq.entity.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * 消费者
 *
 * @author pjmike
 * @create 2018-08-19 17:38
 */
@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receive(User user, Message message, Channel channel) {
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("监听的消息： {}", user.toString());
            //通知MQ 消息已经被消息，手动ACK
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                log.info("确认失败,重新压入MQ");
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    @RabbitListener(queues = RabbitConfig.QUEUE_FANOUT)
    public void receive_fanout_1(User user, Message message, Channel channel) {
        log.info("1_监听的消息： {}", user.toString());
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receive_fanout_2(User user, Message message, Channel channel) {
        log.info("2_监听的消息： {}", user.toString());
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_DIRECT)
    public void receive_direct(User user) {
        log.info("监听的消息： {}", user.toString());
    }

    @RabbitListener(queues = "queue.dlx")
    public void receive_queue_dlx(User user,Message message,Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        String result = new String(message.getBody());

        System.out.println("dead message 6s后 消费消息： "+user.toString());

    }
}
