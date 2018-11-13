package com.pjmike.jwtsecurity.utils;

/**
 * @author pjmike
 * @create 2018-10-05 17:30
 */
public class ResultUtils {
    public static Result success() {
        return new Result(0, "success");
    }

    public static Result failure() {
        return new Result(1, "error happened");
    }

    public static Result success(Object object) {
        return new Result(0, "success",object);
    }

    public static Result failure(String message) {
        return new Result(1, message, null);
    }
}
