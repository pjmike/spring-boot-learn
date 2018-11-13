package com.pjmike.rabbitmq.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-10-16 15:06
 */

public class RpcClient {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";

    public RpcClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("39.106.63.214");
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setPort(5672);
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public String call(String message) throws IOException, InterruptedException {
        //请求的唯一标识
        final String corrId = UUID.randomUUID().toString();
        //回调队列
        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties properties = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("", requestQueueName, properties, message.getBytes("UTF-8"));
        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
        //接收服务端的响应内容
        String ctag = channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response.offer(new String((body), "UTF-8"));
                }
            }
        });
        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }

    public static void main(String[] args) {
        RpcClient fibonacciRpc = null;
        String reponse = null;
        try {
            fibonacciRpc = new RpcClient();
            for (int i = 0; i < 32; i++) {
                String i_str = Integer.toString(i);
                System.out.println(" [x] Requesting fib(" + i_str + ")");
                reponse = fibonacciRpc.call(i_str);
                System.out.println("[.] Got '" + reponse + "'");
            }
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (fibonacciRpc != null) {
                try {
                    fibonacciRpc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
