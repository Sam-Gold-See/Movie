package com.samgoldsee.movie.service;

public interface EmailService {

    /**
     * 请求验证码
     *
     * @param email 目标邮箱
     */
    void sendCode(String email);
}
