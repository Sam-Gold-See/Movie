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
@Schema(title = "UserProfileDTO", description = "用户信息DTO")
public class UserProfileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 用户名
    @Schema(description = "用户名", example = "SamGoldSee", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    // 用户性别(M:男 F:女)
    @Schema(description = "性别(M:男 F:女)", example = "M", requiredMode = Schema.RequiredMode.REQUIRED)
    private Character gender;

    // 头像资源链接
    @Schema(description = "头像资源链接", requiredMode = Schema.RequiredMode.REQUIRED)
    private String avatar;
}
