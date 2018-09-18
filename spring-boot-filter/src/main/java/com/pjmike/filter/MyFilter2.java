package com.pjmike.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pjmike
 * @create 2018-09-12 21:40
 */
public class MyFilter2 implements Filter {
    private Logger logger = LoggerFactory.getLogger(MyFilter2.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter init2");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("对request进行处理2");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("filter destroy2");
    }
}
