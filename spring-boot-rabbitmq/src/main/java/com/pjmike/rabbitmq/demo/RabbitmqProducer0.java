package com.pjmike.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生成者
 *
 * @author pjmike
 * @create 2018-08-08 12:01
 */
public class RabbitmqProducer0 {
    private static final String QUEUE_NAME = "hello-1";
    private static final String DIRECT_EXCHANGE = "exchange_hello_1";
    private static final String ROUTE_KEY = "routingkey";
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
        //
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare(DIRECT_EXCHANGE, "direct", true);
        channel.queueBind(QUEUE_NAME, DIRECT_EXCHANGE, ROUTE_KEY);
        String message = "hello world ";
        // Mandatory 如果为true,则监听器会接收到路由不可达的消息，然后进行后续处里
        // 如果为 false, 那么 broker 端自动删除该消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(DIRECT_EXCHANGE, ROUTE_KEY, true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes()
            );
        }
        //exchange 根据路由键没有路由到队列，即监听不可达的消息
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.err.println("=======handle return=======");
            System.err.println("replyCode: " + replyCode);
            System.err.println("replyText: " + replyText);
            System.err.println("exchange: " + exchange);
            System.err.println("routingkey: " + routingKey);
            System.err.println("properties: " + properties);
            System.err.println("body :" + new String(body));
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭资源
        channel.close();
        connection.close();
    }
}
