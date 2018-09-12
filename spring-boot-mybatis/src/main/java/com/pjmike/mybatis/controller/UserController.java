package com.pjmike.mybatis.controller;

import com.pjmike.mybatis.model.User;
import com.pjmike.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author pjmike
 * @create 2018-09-05 20:26
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public String userMapping(@RequestBody User user){
        userService.insertUser(user);
        return "ok";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
