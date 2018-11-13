package com.pjmike.jwtsecurity.controller;

import com.pjmike.jwtsecurity.entity.User;
import com.pjmike.jwtsecurity.service.AuthService;
import com.pjmike.jwtsecurity.utils.Result;
import com.pjmike.jwtsecurity.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
//    @GetMapping("/signOut")
//    public Result logout(HttpServletRequest request,HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request,response,auth);
//        }
//        return ResultUtils.success("退出成功");
//    }
@GetMapping("/signin")
public String signin() {
    return "你未登录，请先登录";
}
}
