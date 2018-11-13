package com.pjmike.jwtsecurity.exception;

import com.pjmike.jwtsecurity.utils.Result;
import com.pjmike.jwtsecurity.utils.ResultUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.*;
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
}
