package com.pjmike.jwtsecurity.security;

import com.pjmike.jwtsecurity.exception.JwtException;
import com.pjmike.jwtsecurity.jwt.JwtTokenUtil;
import com.pjmike.jwtsecurity.service.JwtUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pjmike
 * @create 2018-10-04 23:41
 */
@Component
@Slf4j
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
    @Value("${jwt.header}")
    private String header;
    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("开始身份验证......");
        String token = httpServletRequest.getHeader(header);
        log.info("token is {}", token);
        String username = null;
        if (token != null) {
            if (jwtTokenUtil.isTokenExpired(token)) {
                log.info("token is expired");
                throw new JwtException("token is expired ,please login again");
            } else {
                log.info("token is not expired");
                username = jwtTokenUtil.getUsernameFromToken(token);
                log.info("name: {}",username);
            }
        } else {
            log.warn("couldn't find token,will ignore the token");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.debug("security context was null, so authorizating user");
            UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
