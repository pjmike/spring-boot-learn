package com.pjmike.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggerApplication {
    private static Logger logger = LoggerFactory.getLogger(LoggerApplication.class);
    public static void main(String[] args) {
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        SpringApplication.run(LoggerApplication.class, args);
    }
}
