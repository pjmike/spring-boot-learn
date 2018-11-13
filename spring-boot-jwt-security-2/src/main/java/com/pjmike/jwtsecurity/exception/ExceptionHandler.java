package com.pjmike.jwtsecurity.exception;

import com.pjmike.jwtsecurity.utils.Result;
import com.pjmike.jwtsecurity.utils.ResultUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.*;

import java.nio.file.AccessDeniedException;

/**
 * @author pjmike
 * @create 2018-10-10 17:30
 */
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleException(Exception e) {
        if (e instanceof BadCredentialsException) {
            return ResultUtils.failure("Bad Credentials： 密码错误");
        }
        return ResultUtils.failure(e.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result forbidden(Exception e) {
        if (e instanceof AccessDeniedException) {
            return ResultUtils.failure("权限不够，禁止访问");
        }
        return ResultUtils.failure(e.getMessage());
    }
}
