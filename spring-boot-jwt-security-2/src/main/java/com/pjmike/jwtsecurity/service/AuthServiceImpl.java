package com.pjmike.jwtsecurity.service;

import com.pjmike.jwtsecurity.entity.Authority;
import com.pjmike.jwtsecurity.entity.AuthorityName;
import com.pjmike.jwtsecurity.entity.User;
import com.pjmike.jwtsecurity.exception.JwtException;
import com.pjmike.jwtsecurity.jwt.JwtTokenUtil;
import com.pjmike.jwtsecurity.repository.UserRepository;
import com.pjmike.jwtsecurity.security.JwtUser;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("查询数据库进行校验");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info("Authentication: {}",authentication);
        log.info("将成功验证的Authentication设置到 SecurityContext中");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtUser jwtUser = (JwtUser) jwtUserDetailService.loadUserByUsername(username);
        String token = jwtTokenUtil.createToken(jwtUser);
        return token;
//        User user = userRepository.findByUsername(username);
//        System.out.println("user: " + user);
//        if (user != null) {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            if (encoder.matches(password, user.getPassword())) {
//                return "ok";
//            } else {
//                throw new JwtException("密码错误");
//            }
//        } else {
//            throw new JwtException("用户不存在");
//        }
    }
    @Override
    public String refresh(String token) {
        return null;
    }
}
