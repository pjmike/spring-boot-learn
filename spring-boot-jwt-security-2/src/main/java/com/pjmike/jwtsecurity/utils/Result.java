package com.pjmike.jwtsecurity.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pjmike
 * @create 2018-10-05 17:29
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 6556298593884541990L;
    private int code;
    private String message;
    private Object data;

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}
