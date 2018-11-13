package com.pjmike.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-10-19 20:42
 */
public class Producer_TTL_Message {
    public static void main(String[] args) throws IOException, TimeoutException {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("39.106.63.214");
        factory.setPort(5672);
        factory.setPassword("root");
        factory.setUsername("root");
        //创建一个连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //创建一个direct exchange
        channel.exchangeDeclare("exchange_111", "direct", true);
        //创建一个queue
        channel.queueDeclare("queue_111", true,false,false,null);
        //绑定 queue 与 exchange
        channel.queueBind("queue_111", "exchange_111", "routingkey");
        String msg = "hello world";
        AMQP.BasicProperties properties = new AMQP.BasicProperties()
                .builder()
                .deliveryMode(2)
                .expiration(10000 + "")
                .build();
        channel.basicPublish("exchange_111", "routingkey", properties, msg.getBytes());
        channel.close();
        connection.close();
    }
}
