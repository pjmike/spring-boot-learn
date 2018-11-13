package com.pjmike.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author pjmike
 * @create 2018-08-08 16:14
 */
public class RabbitmqConsumer {
    private static final String QUEUE_NAME = "hello";
    private static final String IP_ADDRESS = "39.106.63.214";
    private static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses = new Address[]{
                new Address(IP_ADDRESS, PORT)
        };
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        //连接方式与生成者不一样，需要传入地址
        Connection connection = factory.newConnection(addresses);
        //创建信道
        final Channel channel = connection.createChannel();
        //设置客户端最多接收未被ack的消息的个数
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv message: " + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //消费者显示调用Basic.Ack命令
                //deliveryTag可以看做是消息的编号，它是一个位的长整型值
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //可以指定autoAck为false,RabbitMQ会等待消费者显式地回复确认信号后才从内存中移去消息
        channel.basicConsume(QUEUE_NAME, consumer);
        //等待回调函数执行完毕之后，关闭资源
//        GetResponse response = channel.basicGet(QUEUE_NAME, false);
//        System.out.println(new String(response.getBody()));
//        channel.basicAck(response.getEnvelope().getDeliveryTag(),false);
        TimeUnit.SECONDS.sleep(5);
//        channel.close();
//        connection.close();

    }
}
