package com.samgoldsee.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 用户名
    private String name;

    // 用户邮箱地址
    private String email;

    // 用户登录密码
    private String password;

    // 用户性别(M:男 F:女)
    private Character gender;

    // 账号权限(0:普通 1:VIP)
    private Boolean type;

    // 头像资源链接
    private String avatar;
}
