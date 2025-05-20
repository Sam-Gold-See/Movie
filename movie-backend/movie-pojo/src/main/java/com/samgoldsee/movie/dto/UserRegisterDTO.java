package com.samgoldsee.movie.dto;

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
@Schema(description = "用户DTO", title = "UserRegisterDTO")
public class UserRegisterDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 用户名
    @Schema(description = "用户名", example = "SamGoldSee", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    // 用户邮箱地址
    @Schema(description = "邮箱地址", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    // 用户登录密码
    @Schema(description = "登录密码", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    // 用户性别(M:男 F:女)
    @Schema(description = "性别", example = "M", requiredMode = Schema.RequiredMode.REQUIRED)
    private Character gender;
}
