package com.pjmike.autowire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;

//@EnableAutoConfiguration
@SpringBootApplication
public class AutowireApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutowireApplication.class, args);
    }

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("当前WebServer实现类为： " + event.getWebServer()
                .getClass().getName());
    }
}
