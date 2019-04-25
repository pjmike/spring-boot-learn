package com.pjmike.limiter;

import com.google.common.collect.ImmutableList;
import com.pjmike.limiter.annotation.Limit;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 拦截器
 *
 * @author pjmike
 * @create 2019-04-23 16:20
 */
@Aspect
@Configuration
@Log
public class LimiterInterceptor {
    private final RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    public LimiterInterceptor(RedisTemplate<String, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Around("execution(public * * (..)) && @@annotation(com.pjmike.limiter.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        String key;
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix(), key));
        try {
            String luaScript = buildLuaScript();
            RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript);

        } catch (Exception e) {

        }
        return null;
    }

    private String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c");
        lua.append("\nc = redis.call('get', KEYS[1])");
        //调用不超过最大值，则直接返回
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn c;");
        lua.append("\nend");
        //执行计算器自加
        lua.append("\nc = redis.call('incr',KEYS[1]");
        lua.append("\nif tonumber(c) == 1 then");
        //从第一次调用开始限流，设置对应键值的过期
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2]");
        lua.append("\nend");
        lua.append("\nreturn c;");
        return lua.toString();
    }

    private String getIpAddress() {
        return null;
    }
}
