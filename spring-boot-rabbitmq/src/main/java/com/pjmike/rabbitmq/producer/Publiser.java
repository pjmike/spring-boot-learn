package com.pjmike.rabbitmq.producer;

import com.pjmike.rabbitmq.MQConfig.RabbitConfig;
import com.pjmike.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pjmike
 * @create 2018-10-09 14:59
 */
@RestController
public class Publiser {
    private final AmqpTemplate amqpTemplate;
    @Autowired
    public Publiser(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/default")
    public String sendUser() {
        User user = new User();
        user.setId(1);
        user.setName("default");
        amqpTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, user);
        return "send ok";
    }
    @GetMapping("/fanout")
    public String fanout() {
        User user = new User();
        user.setId(1);
        user.setName("fanout");
        amqpTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE,RabbitConfig.QUEUE_FANOUT,user);
        return "send ok";
    }

    @GetMapping("/direct")
    public String direct() {
        User user = new User();
        user.setId(1);
        user.setName("direct");
        amqpTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, RabbitConfig.ROUTE_KEY, user);
        return "send ok";
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
