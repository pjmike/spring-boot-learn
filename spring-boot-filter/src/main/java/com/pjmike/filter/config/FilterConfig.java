package com.pjmike.filter.config;

import com.pjmike.filter.MyFilter;
import com.pjmike.filter.MyFilter2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author pjmike
 * @create 2018-09-13 0:13
 */
@Configuration
public class FilterConfig {
    /**
     * 配置一个Filter注册器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean1() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter1());
        registrationBean.setName("filter1");
        //设置顺序
        registrationBean.setOrder(10);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean2() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter2());
        registrationBean.setName("filter2");
        //设置顺序
        registrationBean.setOrder(3);
        return registrationBean;
    }
    @Bean
    public Filter filter1() {
        return new MyFilter();
    }

    @Bean
    public Filter filter2() {
        return new MyFilter2();
    }
}
