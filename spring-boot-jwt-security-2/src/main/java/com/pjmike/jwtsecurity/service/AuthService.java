package com.pjmike.jwtsecurity.service;

import com.pjmike.jwtsecurity.entity.User;

/**
 * @author pjmike
 * @create 2018-10-05 15:12
 */
public interface AuthService {
    User register(User user);

    String login(String username, String password);

    String refresh(String token);
}
