package com.pjmike.qiniuyun;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 七牛云属性配置
 *
 * @author pjmike
 * @create 2018-08-20 11:52
 */
@Component
@ConfigurationProperties(prefix = "qiniuyun")
public class QiNiuYunProperties {
    /**
     * 七牛云的密钥
     */
    private String accessKey = "accessKey_test";
    private String secretKey = "secretKey_test";
    /**
     * 存储空间名字
     */
    private String bucket = "bucket_test";
    /**
     * 一般设置为cdn
     */
    private String cdnPrefix = "cdn";

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getCdnPrefix() {
        return cdnPrefix;
    }

    public void setCdnPrefix(String cdnPrefix) {
        this.cdnPrefix = cdnPrefix;
    }
}
