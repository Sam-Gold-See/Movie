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
@AllArgsConstructor
@NoArgsConstructor
public class Record implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 记录ID
    private Long id;

    // 电影ID
    private Integer movieId;

    // 用户ID
    private Integer userId;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;
}
