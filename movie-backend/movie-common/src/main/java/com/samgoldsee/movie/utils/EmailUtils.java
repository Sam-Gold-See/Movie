package com.samgoldsee.movie.utils;

import com.samgoldsee.movie.constant.AccountConstant;
import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.exception.EmailException;
import com.samgoldsee.movie.properties.EmailConfigProperties;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;

/**
 * 邮件发送工具类
 */
@Component
public class EmailUtils {

    private static EmailConfigProperties emailConfigProperties;

    /**
     * 由 Spring 初始化 EmailConfigurationProperties
     */
    public EmailUtils(EmailConfigProperties emailConfigProperties) {
        EmailUtils.emailConfigProperties = emailConfigProperties;
    }

    /**
     * 发送验证码邮件（163邮箱）
     *
     * @param toEmail          接收邮箱
     * @param verificationCode 验证码
     */
    public static void sendVerificationCode(String toEmail, String verificationCode) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(emailConfigProperties.getHost()); // SMTP 服务器
            email.setCharset("UTF-8");
            email.setAuthentication(emailConfigProperties.getUsername(), emailConfigProperties.getPassword());
            email.setFrom(emailConfigProperties.getUsername(), "SGSMovie");
            email.setSSLOnConnect(true); // 启用SSL
            email.addTo(toEmail); // 设置收信人
            email.setSubject("登录验证码"); // 邮件主题
            email.setMsg("尊敬的用户，您好！您本次操作的验证码是：" + verificationCode + " ，请在" + AccountConstant.VERIFICATION_CODE_TTL + "分钟内尽快使用。"); // 邮件内容
            email.send();
        } catch (Exception e) {
            throw new EmailException(MessageConstant.SEND_EMAIL_FAIL);
        }
    }
}
