package com.pjmike.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author pjmike
 * @create 2018-08-15 22:32
 */
//@Component
public class ApplicationListenerTest implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent testEvent)
    {
        testEvent.getMessage();
    }
}
