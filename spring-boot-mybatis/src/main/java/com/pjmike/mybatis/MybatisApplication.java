package com.pjmike.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.pjmike.mybatis.repository")
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        System.out.println("热部署测试");
        SpringApplication.run(MybatisApplication.class, args);
    }
}
