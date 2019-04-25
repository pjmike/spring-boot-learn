package com.pjmike.spring.boostrap;

import com.pjmike.spring.annotation.UserConfiguration;
import com.pjmike.spring.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author pjmike
 * @create 2018-11-21 16:29
 */
public class AnnotationBootStrap {
    public static void main(String[] args) {
        // 构建一个 ApplicationContext 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册一个配置 Bean
        context.register(UserConfiguration.class);
        // 调用 refresh 启动容器
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.println("user.getName() = "+user.getUsername());
    }
}
