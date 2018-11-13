package com.pjmike.jwtsecurity.service;

import com.pjmike.jwtsecurity.entity.User;
import com.pjmike.jwtsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author pjmike
 * @create 2018-10-04 16:47
 */
@Service
public class JwtUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("no user found by name : %s.", s));
        } else {
           return JwtUserFactory.create(user);
        }
    }
}
