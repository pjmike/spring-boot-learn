package com.pjmike.filter.config;

import com.pjmike.filter.interceptor.MyInterceptor;
import com.pjmike.filter.interceptor.MyInterceptor2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author pjmike
 * @create 2018-09-13 11:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(handlerInterceptor2())
                .addPathPatterns("/**");
    }
    @Bean
    public HandlerInterceptor handlerInterceptor() {
        return new MyInterceptor();
    }

    @Bean
    public HandlerInterceptor handlerInterceptor2() {
        return new MyInterceptor2();
    }
}
