package com.samgoldsee.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 用户id
    private Integer id;

    // 用户名
    private String name;

    // 用户邮箱地址
    private String email;

    // 用户登录密码
    private String password;

    // 用户性别(M:男 F:女)
    private Character gender;

    // 账号权限(false:普通 true:VIP)
    private Boolean type;

    // 头像资源链接
    private String avatar;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;
}
