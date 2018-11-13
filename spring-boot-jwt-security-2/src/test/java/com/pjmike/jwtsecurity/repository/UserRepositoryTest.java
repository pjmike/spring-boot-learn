package com.pjmike.jwtsecurity.repository;

import com.alibaba.fastjson.JSON;
import com.pjmike.jwtsecurity.entity.Authority;
import com.pjmike.jwtsecurity.entity.AuthorityName;
import com.pjmike.jwtsecurity.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Value("${jwt.header}")
    private String header;
    @Test
    public void save() {
        User user = new User();
        user.setUsername("pjmike2333");
        user.setPassword("45628852");
        user.setEmail("admin@qq.com");
        user.setEnabled(Boolean.TRUE);
        user.setLastPasswordResetDate(new Date());
        Authority authority = new Authority();
        authority.setName(AuthorityName.ROLE_USER);
        user.addAuthority(authority);
        userRepository.save(user);
    }

    @Test
    public void find() {
        User user = userRepository.findByUsername("user");
        System.out.println("user: "+user);
    }

    @Test
    public void authority() {
        Authority authority = authorityRepository.getOne(1);
        System.out.println("authority: "+authority);
    }

}