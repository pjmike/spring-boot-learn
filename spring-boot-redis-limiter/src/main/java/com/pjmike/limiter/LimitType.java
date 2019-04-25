package com.pjmike.limiter;

/**
 * 限流类型
 *
 * @author pjmike
 * @create 2019-04-23 16:20
 */
public enum  LimitType {
    /**
     * 自定义key
     */
    CUSTOMER,
    /**
     * 根据请求者ip
     */
    IP;
}
