package com.pjmike.jwtsecurity.service;

import com.pjmike.jwtsecurity.entity.Authority;
import com.pjmike.jwtsecurity.entity.AuthorityName;
import com.pjmike.jwtsecurity.entity.User;
import com.pjmike.jwtsecurity.jwt.JwtTokenUtil;
import com.pjmike.jwtsecurity.repository.UserRepository;
import com.pjmike.jwtsecurity.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * @author pjmike
 * @create 2018-10-05 15:13
 */
@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public User register(User user) {
        final String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        user.setLastPasswordResetDate(new Date());
        user.setEnabled(Boolean.TRUE);
        user.addAuthority(new Authority(AuthorityName.ROLE_USER));
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtUser jwtUser = (JwtUser) jwtUserDetailService.loadUserByUsername(username);
        String token = jwtTokenUtil.createToken(jwtUser);
        return token;
    }

    @Override
    public String refresh(String token) {
        return null;
    }
}
