package com.pjmike.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/04 17:10
 */
@Aspect
@Component
public class TestAspect {
    @Pointcut("execution(public * com.pjmike.aop..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        //TODO
    }
}
