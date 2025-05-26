package com.samgoldsee.movie.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ConfigurationProperties(prefix = "alipay")
@Configuration
@Data
public class AliPayConfig {

    // 应用ID
    public String appId;

    // 商户私钥
    public String merchantPrivateKey;

    // 支付宝公钥
    public String alipayPublicKey;

    // 服务器[异步通知]页面路径
    public String notifyUrl;

    // 返回路径
    public String returnUrl;

    // 签名方式
    private String signType;

    // 字符编码格式
    private String charset;

    // 支付宝网关
    public String gatewayUrl;

    @PostConstruct
    public void init() {
        // 设置参数
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = this.signType;
        config.appId = this.appId;
        config.merchantPrivateKey = this.merchantPrivateKey;
        config.alipayPublicKey = this.alipayPublicKey;
        config.notifyUrl = this.notifyUrl;
        Factory.setOptions(config);
        System.out.println("=======支付宝SDK初始化成功=======");
    }
}
