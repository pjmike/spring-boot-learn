package com.pjmike.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author pjmike
 * @create 2018-09-12 21:40
 */
public class MyFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter init1");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("对request进行处理1");
        //对request,response进行预处理
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("filter destroy");
    }
}
