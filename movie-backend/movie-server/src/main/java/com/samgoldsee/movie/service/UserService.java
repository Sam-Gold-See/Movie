package com.samgoldsee.movie.service;

import com.samgoldsee.movie.dto.UserRegisterDTO;

public interface UserService {

    /**
     * 用户注册
     *
     * @param userRegisterDTO 用户DTO对象
     */
    void register(UserRegisterDTO userRegisterDTO);
}
