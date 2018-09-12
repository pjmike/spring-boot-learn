package com.pjmike.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private BeanFactory beanFactory;
    @Test
    public void contextLoads() {

    }

    @Test
    public void test() {
        System.out.println("concrete factory is: " + beanFactory.getClass());
        Assert.assertTrue("Factory can't be null",beanFactory != null);
        Demo demo = (Demo) beanFactory.getBean("demo");
        System.out.println("Found the demo bean: "+demo.getClass());
    }

}
