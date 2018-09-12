package com.pjmike.beanutils;

import com.pjmike.beanutils.model.PersonDest;
import com.pjmike.beanutils.model.PersonSource;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;

/**
 * @author pjmike
 * @create 2018-09-10 15:49
 */
public class TestSpringBeanUtils {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        StopWatch stopWatch = new StopWatch();
       //下面只是用于单独测试
        PersonSource personSource = new PersonSource();
        personSource.setId(1);
        personSource.setUsername("pjmike");
        personSource.setAge(1);
        PersonDest personDest = new PersonDest();
        stopWatch.start();
        BeanUtils.copyProperties(personSource,personDest);
        stopWatch.stop();
        System.out.println("Spring 的BeanUtils对象拷贝耗时："+stopWatch.getTotalTimeMillis()+"毫秒");
        System.out.println("persondest: "+personDest);
    }
}
