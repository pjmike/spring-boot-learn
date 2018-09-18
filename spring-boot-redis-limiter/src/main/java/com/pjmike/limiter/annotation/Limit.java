package com.pjmike.limiter.annotation;

import com.pjmike.limiter.LimitType;

import java.lang.annotation.*;

/**
 * 限流
 *
 * @author pjmike
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limit {
    /**
     * 资源的名字
     *
     * @return
     */
    String name() default "";

    /**
     * 资源的key
     *
     * @return
     */
    String key() default "";

    /**
     * Key的prefix
     *
     * @return
     */
    String prefix() default "";

    /**
     * 给定的时间段
     * 单位秒
     *
     * @return
     */
    int period();

    /**
     * 最多的访问限制次数
     *
     * @return
     */
    int count();

    /**
     * 类型
     *
     * @return
     */
    LimitType limitType() default LimitType.CUSTOMER;
}
