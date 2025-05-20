package com.samgoldsee.movie.service;

import com.samgoldsee.movie.dto.CheckCodeDTO;

public interface EmailService {

    /**
     * 请求验证码
     *
     * @param email 目标邮箱
     */
    void sendCode(String email);

    /**
     * 校验验证码
     *
     * @param checkCodeDTO 校验验证码DTO
     */
    void checkCode(CheckCodeDTO checkCodeDTO);
}
