package com.pjmike.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-10-19 20:42
 */
public class Producer_Fanout {
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
        channel.exchangeDeclare("exchange_222", "fanout", true);
        //创建一个queue
        channel.queueDeclare("queue_222", true,false,false,null);
        channel.queueDeclare("queue_333", true,false,false,null);
        //绑定 queue 与 exchange
        channel.queueBind("queue_222", "exchange_222", "routingkey");
        channel.queueBind("queue_333", "exchange_222", "routing");
        String msg = "hello world";
        channel.basicPublish("exchange_222", "routingkey", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        channel.close();
        connection.close();
    }
}
