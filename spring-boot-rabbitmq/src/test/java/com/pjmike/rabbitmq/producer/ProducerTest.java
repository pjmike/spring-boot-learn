package com.pjmike.rabbitmq.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {
    @Autowired
    private Producer producer;
    @Test
    public void sender() {
        producer.sender("hello world");
    }

    @Test
    public void sendTopic() {
        producer.topicSender("hello topic");
    }
}