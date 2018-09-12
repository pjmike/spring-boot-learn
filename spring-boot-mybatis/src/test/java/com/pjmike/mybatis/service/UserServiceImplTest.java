package com.pjmike.mybatis.service;

import com.pjmike.mybatis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("li ning");
        user.setPassword("123456");
        userService.insertUser(user);
    }
}