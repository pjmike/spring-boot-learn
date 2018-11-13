package com.pjmike.rabbitmq.demo;

import com.rabbitmq.client.*;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-10-15 21:14
 */
public class RabbitmqPublisher5 {
    private static final String QUEUE_NAME = "queue_demo_3";
    private static final String IP_ADDRESS = "39.106.63.214";
    /**
     * RabbitMQ服务端默认端口号为5672
     */
    private static final int PORT = 5672;
    public static void main(String[] args) throws IOException, TimeoutException {
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
        SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());
        channel.queueDeclare(QUEUE_NAME, true, false, false,null);

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("RabbitMQ Server端,消息确认收到, SeqNo: " + deliveryTag + ", multiple: " + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息丢失，获得Nack命令,SeqNo: " + deliveryTag + ", multiple: " + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }
        });
        //一直发送消息的场景
        for (int i = 0; i < 100; i++) {
            long nextSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, "pjmike".getBytes("UTF-8"));
            confirmSet.add(nextSeqNo);
        }
    }
}
