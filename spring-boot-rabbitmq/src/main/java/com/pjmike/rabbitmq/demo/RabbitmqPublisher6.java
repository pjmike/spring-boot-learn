package com.pjmike.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-10-15 21:14
 */
public class RabbitmqPublisher6 {
    private static final String QUEUE_NAME = "queue_demo_3";
    private static final String IP_ADDRESS = "39.106.63.214";
    /**
     * RabbitMQ服务端默认端口号为5672
     */
    private static final int PORT = 5672;
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        channel.confirmSelect();
        channel.queueDeclare(QUEUE_NAME, true, false, false,null);
        channel.confirmSelect();
        //一直发送消息的场景
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, "pjmike".getBytes("UTF-8"));
            if (!channel.waitForConfirms()) {
                System.out.println("Send message failed");
            }
        }
        channel.close();
        connection.close();
    }
}
