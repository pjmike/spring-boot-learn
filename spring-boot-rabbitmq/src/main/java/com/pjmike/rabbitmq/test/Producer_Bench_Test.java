package com.pjmike.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-10-19 20:42
 */
public class Producer_Bench_Test {
    public static void main(String[] args) throws IOException, TimeoutException {
        StopWatch stopWatch = new StopWatch();
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
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            channel.basicPublish("exchange_111", "routingkey", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        }
        stopWatch.stop();
        System.out.println("普通模式, 发送 1W 条消息所执行的时间: " + stopWatch.getTotalTimeMillis() + "ms");
        channel.close();
        connection.close();
    }
}
