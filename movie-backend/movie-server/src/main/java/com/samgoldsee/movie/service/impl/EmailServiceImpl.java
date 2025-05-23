package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.constant.AccountConstant;
import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.dto.CheckCodeDTO;
import com.samgoldsee.movie.exception.EmailException;
import com.samgoldsee.movie.exception.VerificationException;
import com.samgoldsee.movie.service.EmailService;
import com.samgoldsee.movie.utils.CodeUtils;
import com.samgoldsee.movie.utils.EmailUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 请求验证码
     *
     * @param email 目标邮箱
     */
    @Override
    public void sendCode(String email) {
        String verificationCodeRedis = stringRedisTemplate.opsForValue().get(email);
        if (verificationCodeRedis != null && !verificationCodeRedis.isEmpty())
            throw new EmailException(MessageConstant.EMAIL_REPEAT);

        String verificationCode = RandomStringUtils.secure().next(6, AccountConstant.VERIFICATION_CODE_CHARS);
        EmailUtils.sendVerificationCode(email, verificationCode);

        stringRedisTemplate.opsForValue().set(AccountConstant.REDIS_KEY + email, verificationCode, AccountConstant.VERIFICATION_CODE_TTL, TimeUnit.MINUTES);
    }

    @Override
    public void checkCode(CheckCodeDTO checkCodeDTO) {
        String verificationCode = CodeUtils.upperLetters(checkCodeDTO.getVerificationCode());
        String email = checkCodeDTO.getEmail();

        String verificationCodeRedis = stringRedisTemplate.opsForValue().get(AccountConstant.REDIS_KEY + email);

        // 验证码比对
        if (verificationCodeRedis == null || !Objects.equals(verificationCode, verificationCodeRedis))
            throw new VerificationException(MessageConstant.VERIFICATION_CODE_ERROR);

        // 验证码正确，设置 Redis 中的验证码状态为 True
        stringRedisTemplate.opsForValue().set(AccountConstant.REDIS_KEY + email, AccountConstant.PERMISSION.toString(), AccountConstant.VERIFICATION_CODE_TTL, TimeUnit.MINUTES);
    }
}
