package com.pjmike.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 测试类
 *
 * @author pjmike
 * @create 2018-09-05 9:30
 */
@Component(value = "demo")
public class Demo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
