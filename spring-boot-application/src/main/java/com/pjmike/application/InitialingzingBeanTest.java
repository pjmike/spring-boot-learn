package com.pjmike.application;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author pjmike
 * @create 2018-08-14 17:46
 */
@Component
public class InitialingzingBeanTest implements InitializingBean {
    private LazyTest lazyTest = new LazyTest();
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean..");
    }
}
