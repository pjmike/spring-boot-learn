package com.pjmike.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author pjmike
 * @create 2018-11-21 10:34
 */
public class Test {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        Demo demo = (Demo) beanFactory.getBean("demo");
        demo.setName("pj");
        System.out.println(demo.getName());
    }
}
