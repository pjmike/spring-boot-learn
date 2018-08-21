package com.pjmike.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pjmike
 * @create 2018-08-16 11:48
 */
@RestController
public class TestEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("/hello")
    public String publish() {
        TestEvent event = new TestEvent("");
        event.setMessage("hello");
        publisher.publishEvent(event);
        return "ok";
    }
}
