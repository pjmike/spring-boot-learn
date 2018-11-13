package com.pjmike.rabbitmq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-10-15 21:14
 */
public class RabbitmqPublisher {
    private static final String QUEUE_NAME = "queue_demo_2";
    private static final String IP_ADDRESS = "39.106.63.214";
    /**
     * RabbitMQ服务端默认端口号为5672
     */
    private static final int PORT = 5672;
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        HashMap<String, Object> argss = new HashMap<>();
        argss.put("x-message-ttl", 6000);
        //通过队列属性设置消息TTL
        channel.queueDeclare(QUEUE_NAME, true, false, false, argss);

        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, "hello,world".getBytes());

        channel.close();
        connection.close();
    }
}
