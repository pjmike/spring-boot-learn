package com.pjmike.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 生成者
 *
 * @author pjmike
 * @create 2018-08-08 12:01
 */
public class Producer_AE {


    public static void main(String[] args) throws IOException, TimeoutException {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置ip
        factory.setHost("39.106.63.214");
        //设置端口
        factory.setPort(5672);
        //设置账号
        factory.setUsername("root");
        //设置密码
        factory.setPassword("root");
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        Map<String, Object> map = new HashMap<>();
        map.put("alternate-exchange", "myAE");
        channel.exchangeDeclare("normalExchange", "direct", true, false, map);
        channel.exchangeDeclare("myAE", "fanout", true, false, null);
        channel.queueDeclare("normalQueue", true, false, false, null);
        channel.queueBind("normalQueue", "normalExchange", "normalKey");
        channel.queueDeclare("unroutedQueue", true, false, false, null);
        channel.queueBind("unroutedQueue", "myAE", "");
        //如果交换器无法通过路由键找到一个合适的队列，那么消息就会发送到备用的交换器myAE中去
        //此时，消息被重新发送到备份交换器时的路由键和从生产者发出的路由键一样，所有备份路由器的类型一般为fanout,这样，可以直接发送给绑定的队列，而不存在，备份交换器通过路由键也找不到队列发送而导致消息丢失的情况
        channel.basicPublish("normalExchange", "errorKey", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes());
        channel.close();
        connection.close();
    }

}
