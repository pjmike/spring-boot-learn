package com.pjmike.beanutils;

import com.pjmike.beanutils.model.PersonDest;
import com.pjmike.beanutils.model.PersonSource;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;

/**
 * @author pjmike
 * @create 2018-09-10 15:49
 */
public class TestApacheBeanUtils {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
       //下面只是用于测试
        PersonSource personSource = new PersonSource();
        personSource.setId(1);
        personSource.setUsername("pjmike");
        PersonDest personDest = new PersonDest();
        BeanUtils.copyProperties(personDest,personSource);
        System.out.println("persondest: "+personDest);
    }
}
