package com.pjmike.jwtsecurity.controller;

import com.pjmike.jwtsecurity.entity.User;
import com.pjmike.jwtsecurity.service.AuthService;
import com.pjmike.jwtsecurity.utils.Result;
import com.pjmike.jwtsecurity.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author pjmike
 * @create 2018-10-05 17:20
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        User result = authService.register(user);
        log.info("user ： {}",user);
        return ResultUtils.success(result);
    }
    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletResponse response) {
        String token = authService.login(user.getUsername(), user.getPassword());
        response.addHeader("Authorization",token);
        return ResultUtils.success();
    }

    @GetMapping("/index")
    public Result index() {
        return ResultUtils.success("index");
    }

    @GetMapping("/vip")
    public Result vip() {
        return ResultUtils.success("vip会员");
    }

    @GetMapping("/admin")
    public Result admin() {
        return ResultUtils.success("管理员登录");
    }

    @GetMapping("/user")
    public Result userView() {
        return ResultUtils.success("用户访问");
    }

    @GetMapping("/sign")
    public Result signin() {
        return ResultUtils.success("请登录");
    }
}
