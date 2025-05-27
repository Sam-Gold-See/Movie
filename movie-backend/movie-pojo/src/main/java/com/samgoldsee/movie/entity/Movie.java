package com.samgoldsee.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 电影id
    private Integer id;

    // 电影名
    private String name;

    // 简介
    private String description;

    // 电影地区id
    private Integer zoneId;

    // 浏览数
    private Long view;

    // 海报链接
    private String poster;

    // 观看权限(0:普通 1:VIP)
    private Boolean permission;

    // 上线日期
    private Date releaseDate;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;
}
