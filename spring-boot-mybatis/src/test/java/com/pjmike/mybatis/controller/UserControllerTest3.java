package com.pjmike.mybatis.controller;

import com.pjmike.mybatis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest3 {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    @Transactional
    public void userMapping() throws Exception {
        User user = new User();
        user.setUsername("pjpj");
        user.setPassword("123456");
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/user", user, String.class);
        System.out.println("Result: "+responseEntity.getBody());
        System.out.println("状态码: "+responseEntity.getStatusCodeValue());
    }
}