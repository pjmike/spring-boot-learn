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
public class RabbitmqProducer2 {
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
        //创建一个type="direct",持久化的,非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
        //创建一个持久化，非排他，非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        //发送一条持久化的消息:hello world
        String message = "hello  pjmike";
        channel.basicPublish(EXCHANGE_NAME, "", true, true,MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        //生产者获取到没有被正确路由到合适队列的消息,通过调用channel.addReturnListener来添加ReturnListener监听器来实现
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("Basic.Return返回的结果是: " + message);
            }
        });
//        channel.close();
//        connection.close();
    }

}
