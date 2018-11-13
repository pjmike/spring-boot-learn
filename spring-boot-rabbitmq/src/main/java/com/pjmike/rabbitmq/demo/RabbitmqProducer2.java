package com.pjmike.rabbitmq.demo;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.DefaultExceptionHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生成者
 *
 * @author pjmike
 * @create 2018-08-08 12:01
 */
public class RabbitmqProducer2 {
    private static final String EXCHANGE_NAME = "exchange_1";
    private static final String ROUTE_KEY = "route key";
    private static final String QUEUE_NAME = "queue_1";
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
        factory.setExceptionHandler(new DefaultExceptionHandler() {
            @Override
            public void handleConfirmListenerException(Channel channel, Throwable exception) {
                System.out.println("消息确认失败");
                exception.printStackTrace();
            }
        });
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建一个type="direct",持久化的,非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true);
        //创建一个持久化，非排他，非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY);
        String message = "hello  pjmike";

        channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.close();
        connection.close();
    }
}
