package com.pjmike.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 批量 Confirm模式
 *
 * @author pjmike
 * @create 2018-10-19 20:42
 */
public class Producer_Confirm_Batch {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
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
        // 开启发送端确认模式
        channel.confirmSelect();
        for (int i = 0; i < 10000; i++) {
            channel.basicPublish("exchange_111", "routingkey", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        }
        if (channel.waitForConfirms()) {
            System.out.println("消息发送成功");
        } else {
            //TODO 消息重发，可能会造成重复消息
        }
        stopWatch.stop();
        System.out.println("发送1w条数据耗时： " + stopWatch.getTotalTimeMillis() + "ms");
        channel.close();
        connection.close();
    }
}
