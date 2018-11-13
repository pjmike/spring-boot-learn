package com.pjmike.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author pjmike
 * @create 2018-08-08 16:14
 */
public class Consumer_Autoack {
    private static final String IP_ADDRESS = "39.106.63.214";
    private static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses = new Address[]{
                new Address(IP_ADDRESS, PORT)
        };
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection = factory.newConnection(addresses);
        //创建信道
        final Channel channel = connection.createChannel();
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("envelope: " + envelope);
                System.out.println("properties: " + properties);
                System.out.println("recv message: " + new String(body));
                System.out.println("消息唯一ID: " + envelope.getDeliveryTag());
            }
        };
        channel.basicConsume("queue_111",true,consumer);
    }
}
