package com.pjmike.rabbitmq.producer;

import com.pjmike.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author pjmike
 * @create 2018-10-09 14:59
 */
@RestController
public class Publiser2 {
    private final AmqpTemplate amqpTemplate;
    @Autowired
    public Publiser2(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/dlx")
    public String dlx() {
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties properties = message.getMessageProperties();
            //设置编码
            properties.setContentEncoding("utf-8");
            // TODO 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            properties.setExpiration(10*1000+"");
            return message;
        };
        User user = new User();
        user.setId(1);
        user.setName("pjmike");
        amqpTemplate.convertAndSend("exchange.normal","",user,messagePostProcessor);
        return "ok";
    }
}
