package com.pjmike.qiniuyun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * 校验类
 *
 * @author pjmike
 * @create 2018-08-20 22:24
 */
public class QiNiuYunCondition implements Condition {
    private static Logger logger = LoggerFactory.getLogger(QiNiuYunCondition.class);
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("qiniuyun.accessKey");
        if (StringUtils.isEmpty(property)) {
            throw new RuntimeException("没有七牛云的配置");
        } else {
            return true;
        }
    }
}
