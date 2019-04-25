package com.pjmike.spring.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author pjmike
 * @create 2018-11-21 15:41
 */
public class User implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, BeanPostProcessor,
        InitializingBean, DisposableBean {
    private Integer id;

    private String username;

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoader被调用....");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware被调用...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware被调用...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean被调用...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean被调用...");
    }
}
