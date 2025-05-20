package com.samgoldsee.movie.constant;

/**
 * 账号信息常量类
 */
public class AccountConstant {

    // 验证码有效时长（分钟）
    public static final Integer VERIFICATION_CODE_TTL = 5;

    // 验证码字符数组
    public static final char[] VERIFICATION_CODE_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Redis 存储验证码键常量
    public static final String REDIS_KEY = "code:list:";
}
