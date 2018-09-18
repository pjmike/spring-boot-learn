package com.pjmike.filter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
/**
 * @author pjmike
 * @create 2018-09-13 11:04
 */
@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/hello")
    public Map<String, String> hello(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        logger.info("username:{},password:{}", username, password);
        return map;
    }
}
