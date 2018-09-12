package com.pjmike.jpa.dao;

import com.pjmike.jpa.entity.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRepositoryTest {
    @Autowired
    private DemoRepository demoRepository;

    @Test
    public void test() {
        Demo demo = new Demo();
        demo.setContent("hello world");
        demoRepository.save(demo);
    }
}