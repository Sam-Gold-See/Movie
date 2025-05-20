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
@Schema(description = "校验验证码DTO", title = "CheckCodeDTO")
public class CheckCodeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 邮箱地址
    @Schema(description = "邮箱地址", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    // 验证码
    @Schema(description = "验证码", example = "ABCDEF", requiredMode = Schema.RequiredMode.REQUIRED)
    private String verificationCode;
}
