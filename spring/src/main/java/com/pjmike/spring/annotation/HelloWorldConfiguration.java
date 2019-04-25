package com.pjmike.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pjmike
 * @create 2018-11-22 15:57
 */
public class HelloWorldConfiguration {
    @Bean
    public String helloworld() {
        return "hello world";
    }
}
