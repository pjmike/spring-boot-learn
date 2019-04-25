package com.pjmike.spring;

import com.pjmike.spring.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        beanFactory.addBeanPostProcessor(new User());
//        User user = (User) beanFactory.getBean("user");
//        System.out.println(user.toString());
    }
}
