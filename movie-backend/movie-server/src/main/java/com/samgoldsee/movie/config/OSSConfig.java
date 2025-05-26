package com.samgoldsee.movie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云OSS相关属性配置
 */
@ConfigurationProperties(prefix = "aliyun.oss")
@Configuration
@Data
public class OSSConfig {

    // API端点
    private String endpoint;

    // 通行ID
    private String accessKeyId;

    // 通行密钥
    private String accessKeySecret;

    // Bucket名
    private String bucketName;
}
