package com.pjmike.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 生成者
 *
 * @author pjmike
 * @create 2018-08-08 12:01
 */
public class RabbitmqProducer4 {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "39.106.63.214";
    /**
     * RabbitMQ服务端默认端口号为5672
     */
    private static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置ip
        factory.setHost(IP_ADDRESS);
        //设置端口
        factory.setPort(PORT);
        //设置账号
        factory.setUsername("root");
        //设置密码
        factory.setPassword("root");
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 6000);
        channel.exchangeDeclare("exchange", "direct", true, false, null);
        channel.queueDeclare("queue", true, false, false, map);
        channel.queueBind("queue", "exchange", "key");
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        //持久化消
        builder.deliveryMode(2);
        //设置TTL=60000ms
        builder.expiration("60000");
        AMQP.BasicProperties properties = builder.build();
        channel.basicPublish("exchange", "key", properties, "hello".getBytes());
        channel.close();
        connection.close();
    }

}
