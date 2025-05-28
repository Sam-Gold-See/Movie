package com.samgoldsee.movie.vo;

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
public class ReportVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 记录ID
    private Long recordId;

    // 用户名
    private String userName;

    // 用户性别
    private String gender;

    // 电影名
    private String movieName;

    // 开始时间
    private LocalDateTime startTime;

    // 结束时间
    private LocalDateTime endTime;
}
