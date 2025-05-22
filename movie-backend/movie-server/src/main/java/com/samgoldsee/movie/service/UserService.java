package com.samgoldsee.movie.service;

import com.samgoldsee.movie.dto.UserPasswordDTO;
import com.samgoldsee.movie.dto.UserRegisterDTO;

public interface UserService {

    /**
     * 用户注册
     *
     * @param userRegisterDTO 用户注册DTO对象
     */
    void register(UserRegisterDTO userRegisterDTO);

    /**
     * 用户修改密码
     *
     * @param userPasswordDTO 用户修改密码DTo
     */
    void updatePassword(UserPasswordDTO userPasswordDTO);
}
