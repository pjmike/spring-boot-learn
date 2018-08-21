package com.pjmike.rabbitmq;

import com.pjmike.rabbitmq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RabbitmqApplication {
    @Autowired
    private Producer producer;
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        for (int i = 0; i < 10000; i++) {
//            producer.sender("hello "+i);
//        }
//        stopWatch.stop();
//        System.out.println("发送消息耗时: "+stopWatch.getTotalTimeMillis());
//    }
}
