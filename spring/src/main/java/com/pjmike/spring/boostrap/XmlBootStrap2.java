package com.pjmike.spring.boostrap;

import com.pjmike.spring.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author pjmike
 * @create 2018-11-21 15:41
 */
public class XmlBootStrap2 {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        ClassPathResource resource = new ClassPathResource("F:\\IDEAproject\\spring-boot-learn\\spring\\src\\main\\resources\\META-INF\\spring\\context.xml",XmlBootStrap2.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        factory.addBeanPostProcessor(new User());
        User user = factory.getBean("user", User.class);
        System.out.println("user.getName() = " + user.getUsername());
    }
}
