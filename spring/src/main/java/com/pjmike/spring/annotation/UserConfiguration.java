package com.pjmike.spring.annotation;

import com.pjmike.spring.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pjmike
 * @create 2018-11-21 16:28
 */
@Configuration
public class UserConfiguration {
    @Bean(name = "user")
    public User user() {
        User user = new User();
        user.setUsername("pj");
        return user;
    }
}
