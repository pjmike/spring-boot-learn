package com.pjmike.limiter;

/**
 * 限流类型
 *
 * @author pjmike
 * @create 2018-09-14 16:17
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
