package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.constant.AccountConstant;
import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.constant.OSSConstant;
import com.samgoldsee.movie.dto.UserPasswordDTO;
import com.samgoldsee.movie.dto.UserProfileDTO;
import com.samgoldsee.movie.dto.UserRegisterDTO;
import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.entity.User;
import com.samgoldsee.movie.exception.AccountException;
import com.samgoldsee.movie.exception.VerificationException;
import com.samgoldsee.movie.mapper.UserMapper;
import com.samgoldsee.movie.service.FileService;
import com.samgoldsee.movie.service.UserService;
import com.samgoldsee.movie.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private FileService fileService;

    /**
     * 用户注册
     *
     * @param userRegisterDTO 用户注册DTO对象
     */
    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        // 获取用户邮箱和验证码
        String email = userRegisterDTO.getEmail();

        // 校验验证码
        checkVerificationCodeStatus(email);

        User userDB = userMapper.getByEmail(email);
        if (userDB != null)
            // 账号已存在
            throw new AccountException(MessageConstant.ACCOUNT_ALREADY_EXIST);

        // 复制数据
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);

        // 加密密码
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        // 设置账号权限
        user.setType(AccountConstant.NORMAL_TYPE);

        // 插入数据库
        userMapper.insert(user);
    }

    /**
     * 用户修改密码
     *
     * @param userPasswordDTO 用户修改密码DTo
     */
    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        // 获取用户邮箱和验证码
        String email = userPasswordDTO.getEmail();

        // 校验验证码
        checkVerificationCodeStatus(email);

        User userDB = userMapper.getByEmail(email);
        if (userDB == null)
            // 账号不存在
            throw new AccountException(MessageConstant.ACCOUNT_NOT_EXISTS);

        User user = User.builder()
                .id(userDB.getId())
                .password(passwordEncoder.encode(userPasswordDTO.getPassword()))
                .build();

        userMapper.update(user);
    }

    /**
     * 查询用户信息
     *
     * @param id 用户ID
     */
    @Override
    public UserVO getProfile(Integer id) {
        User userDB = userMapper.getById(id);
        if (userDB == null)
            // 账号不存在
            throw new AccountException(MessageConstant.ACCOUNT_NOT_EXISTS);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDB, userVO);

        return userVO;
    }

    /**
     * 用户修改信息
     *
     * @param userProfileDTO 用户信息DTO
     */
    @Override
    public void updateProfile(UserProfileDTO userProfileDTO) {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = new User();
        BeanUtils.copyProperties(userProfileDTO, user);
        user.setId(userSession.getId());

        userMapper.update(user);
    }

    /**
     * 用户上传头像
     *
     * @param file 上传文件
     */
    @Override
    public void uploadAvatar(MultipartFile file) {
        String avatar = fileService.upload(file, OSSConstant.USER_PATH);

        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = User.builder()
                .id(userSession.getId())
                .avatar(avatar)
                .build();

        userMapper.update(user);
    }

    /**
     * 实现基于Spring Security的用户身份验证和控制
     *
     * @param email 用户邮箱地址
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // 检查用户是否存在
        User user = userMapper.getByEmail(email);
        if (user == null)
            throw new AccountException(MessageConstant.ACCOUNT_NOT_EXISTS);

        return new UserSession(user);
    }

    /**
     * 校验验证码
     *
     * @param email 邮箱地址
     */
    private void checkVerificationCodeStatus(String email) {
        String verificationCodeRedis = stringRedisTemplate.opsForValue().get(AccountConstant.REDIS_KEY + email);

        // 验证码比对
        if (verificationCodeRedis == null || !Objects.equals(verificationCodeRedis, AccountConstant.PERMISSION.toString()))
            throw new VerificationException(MessageConstant.PERMISSION_ERROR);

        // 验证码正确，删除 Redis 中的验证码
        stringRedisTemplate.delete(AccountConstant.REDIS_KEY + email);
    }
}
