package com.samgoldsee.movie.service;

import com.samgoldsee.movie.dto.UserPasswordDTO;
import com.samgoldsee.movie.dto.UserProfileDTO;
import com.samgoldsee.movie.dto.UserRegisterDTO;
import com.samgoldsee.movie.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

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
     * @param userPasswordDTO 用户修改密码DTO
     */
    void updatePassword(UserPasswordDTO userPasswordDTO);

    /**
     * 查询用户信息
     *
     * @param id 用户ID
     */
    UserVO getProfile(Integer id);

    /**
     * 用户修改信息
     *
     * @param userProfileDTO 用户信息DTO
     */
    void updateProfile(UserProfileDTO userProfileDTO);

    /**
     * 用户上传头像
     *
     * @param file 上传文件
     */
    void uploadAvatar(MultipartFile file);
}
