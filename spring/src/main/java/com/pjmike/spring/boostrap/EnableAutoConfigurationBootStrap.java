package com.pjmike.spring.boostrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author pjmike
 * @create 2018-11-22 15:53
 */
@EnableAutoConfiguration
public class EnableAutoConfigurationBootStrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .web(WebApplicationType.NONE)
                .run(args);


        //关闭上下文
        context.close();

    }
}
