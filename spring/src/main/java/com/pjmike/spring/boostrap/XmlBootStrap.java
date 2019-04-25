package com.pjmike.spring.boostrap;

import com.pjmike.spring.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author pjmike
 * @create 2018-11-21 15:41
 */

public class XmlBootStrap {
    public static void main(String[] args) {
        //构建一个 ApplicationContext 上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        //设置此应用上下文的配置路径
        context.setConfigLocations("classpath:/META-INF/spring/context.xml");
        //调用 refresh 方法，完成配置的解析、各种BeanFactoryPostProcessor和BeanPostProcessor的注册、国际化配置的初始化、web内置容器的构造
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.print("user.getName() = "+ user.getUsername());
    }
}