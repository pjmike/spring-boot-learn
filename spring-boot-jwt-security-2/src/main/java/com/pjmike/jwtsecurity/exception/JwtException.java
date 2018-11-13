package com.pjmike.jwtsecurity.exception;

/**
 * @author pjmike
 * @create 2018-10-04 23:56
 */
public class JwtException extends RuntimeException {
    public JwtException() {
        super();
    }

    public JwtException(String message) {
        super(message);
    }
}
