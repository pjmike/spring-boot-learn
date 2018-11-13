package com.pjmike.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-10-19 20:42
 */
public class Producer_Relay_Retry {
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
        //创建一个work exchange
        channel.exchangeDeclare("work_exchange", "direct", true);
        channel.exchangeDeclare("wait_exchange", "direct", true);
        HashMap<String, Object> work_map = new HashMap<>();
        work_map.put("x-dead-letter-exchange", "wait_exchange");
        work_map.put("x-dead-letter-routing-key", "routingkey1");
        //创建一个queue,同时设置DLX
        channel.queueDeclare("work_queue", true, false, false, work_map);
        channel.exchangeDeclare("work_exchange", "direct", true);
        channel.queueBind("work_queue", "work_exchange", "work");
        HashMap<String, Object> wait_map = new HashMap<>();
        wait_map.put("x-dead-letter-exchange", "work_exchange");
        wait_map.put("x-dead-letter-routing-key", "routingkey2");
        wait_map.put("x-message-ttl", 10000);
        //创建一个死信队列
        channel.queueDeclare("wait_queue", true, false, false,wait_map);
        channel.queueBind("wait_queue", "wait_exchange", "routingkey1");
        channel.queueBind("wait_queue", "work_exchange", "routingkey2");
        String msg = "hello world";
        channel.basicPublish("work_exchange", "work", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        channel.close();
        connection.close();
    }
}
