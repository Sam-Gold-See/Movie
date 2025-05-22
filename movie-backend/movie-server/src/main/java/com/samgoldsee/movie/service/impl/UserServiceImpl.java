package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.constant.AccountConstant;
import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.dto.UserRegisterDTO;
import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.entity.User;
import com.samgoldsee.movie.exception.AccountException;
import com.samgoldsee.movie.exception.VerificationException;
import com.samgoldsee.movie.mapper.UserMapper;
import com.samgoldsee.movie.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
     * 实现基于Spring Security的用户身份验证和控制
     *
     * @param email 用户邮箱地址
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // 检查用户是否存在
        User user = userMapper.getByEmail(email);
        if (user == null)
            throw new AccountException(MessageConstant.USER_NOT_EXISTS);

        return new UserSession(user);
    }

    private void checkVerificationCodeStatus(String email) {
        String verificationCodeRedis = stringRedisTemplate.opsForValue().get(AccountConstant.REDIS_KEY + email);

        // 验证码比对
        if (verificationCodeRedis == null || !Objects.equals(verificationCodeRedis, AccountConstant.PERMISSION.toString()))
            throw new VerificationException(MessageConstant.PERMISSION_ERROR);

        // 验证码正确，删除 Redis 中的验证码
        stringRedisTemplate.delete(AccountConstant.REDIS_KEY + email);
    }
}
