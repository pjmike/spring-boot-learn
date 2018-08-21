package com.pjmike.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author pjmike
 * @create 2018-08-16 12:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void publishTest() {
        TestEvent testEvent = new TestEvent("");
        testEvent.setMessage("hello world");
        applicationContext.publishEvent(testEvent);
    }
}
