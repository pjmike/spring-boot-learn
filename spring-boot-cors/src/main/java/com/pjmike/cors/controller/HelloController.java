package com.pjmike.cors.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author pjmike
 * @create 2018-09-06 15:10
 */
@RestController
@CrossOrigin(origins = "*")
public class HelloController {
    @RequestMapping("/hello")
    @CrossOrigin(origins = "*")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/index")
    public String index(HttpServletResponse response) {
        response.addHeader("Access-Allow-Control-Origin","*");
        return "index";
    }
}
