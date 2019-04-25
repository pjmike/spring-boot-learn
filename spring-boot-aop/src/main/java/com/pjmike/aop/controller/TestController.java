package com.pjmike.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/04 17:08
 */
@RestController
public class TestController {
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", "pjmike");
        return "登录成功";
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName()+" = "+cookie.getValue());
        }
        if (session.getAttribute("name").equals("pjmike")) {
            System.out.println("该用户已登录");
            return "ok";
        } else {
            System.out.println("登录失败请重新登录");
            return "error";
        }
    }
}
