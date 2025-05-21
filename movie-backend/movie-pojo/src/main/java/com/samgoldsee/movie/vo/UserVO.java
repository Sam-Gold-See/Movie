package com.samgoldsee.movie.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "用户信息VO", title = "UserVO")
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 用户名
    @Schema(description = "用户名", example = "SamGoldSee")
    private String name;

    // 用户邮箱地址
    @Schema(description = "邮箱地址", example = "user@example.com")
    private String email;

    // 用户性别(M:男 F:女)
    @Schema(description = "性别(M:男 F:女)", example = "M")
    private Character gender;

    // 账号权限(false:普通 true:VIP)
    @Schema(description = "账号权限(false:普通 true:VIP)", example = "1")
    private Boolean type;

    // 头像资源链接
    @Schema(description = "头像资源链接")
    private String avatar;
}
