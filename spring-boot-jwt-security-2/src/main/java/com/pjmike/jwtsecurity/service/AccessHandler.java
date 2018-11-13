package com.pjmike.jwtsecurity.service;

import com.alibaba.fastjson.JSON;
import com.pjmike.jwtsecurity.utils.Result;
import com.pjmike.jwtsecurity.utils.ResultUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author pjmike
 * @create 2018-10-13 11:54
 */
@Component
public class AccessHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String result = JSON.toJSONString(ResultUtils.failure("403"));
        printWriter.write(result);
        printWriter.flush();

    }
}
