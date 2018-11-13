package com.pjmike.rabbitmq.MQConfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author pjmike
 * @create 2018-10-09 14:57
 */
@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "queue_test_demo_1";
    public static final String QUEUE_FANOUT = "queue_test_fanout";
    public static final String QUEUE_DIRECT = "queue_test_direct";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String ROUTE_KEY = "route key";
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Queue queue_fanout() {
        return new Queue(QUEUE_FANOUT, true, false, false);
    }

    @Bean
    public Queue queue_direct() {
        return new Queue(QUEUE_DIRECT, true, false,false);
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE,true,false);
    }

    @Bean
    public Binding bind_fanoutExchange_1() {
        return BindingBuilder.bind(queue_fanout()).to(fanoutExchange());
    }

    @Bean
    public Binding bind_fanoutExchange_2() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding bind_directExchange() {
        return BindingBuilder.bind(queue_direct()).to(directExchange()).with(ROUTE_KEY);
    }

    /**
     * 死信邮箱
     *
     * @return
     */
    @Bean
    public DirectExchange dlx() {
        return new DirectExchange("exchange.dlx", true,false);
    }

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("exchange.normal", true, false);
    }

    /**
     *死信队列
     *
     * @return
     */
    @Bean
    public Queue queue_dlx() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 6000);
        map.put("x-dead-letter-exchange", "exchange.dlx");
        map.put("x-dead-letter-routing-key", "routingkey");
        return new Queue("queue.normal", true, false, false,map);
    }

    @Bean
    public Queue queue_d() {
        return new Queue("queue.dlx", true, false, false, null);
    }

    @Bean
    public Binding bind_exchange_normal_queue_normal() {
        return BindingBuilder.bind(queue_dlx()).to(fanout());
    }
    @Bean
    public Binding bind_exchange_dlx_queue_dlx() {
        return BindingBuilder.bind(queue_d()).to(dlx()).with("routingkey");
    }
}
