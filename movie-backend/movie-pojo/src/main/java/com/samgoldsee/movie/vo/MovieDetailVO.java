package com.samgoldsee.movie.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "电影详情VO", title = "MovieDetailVO")
public class MovieDetailVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 电影id
    @Schema(description = "电影id", example = "1")
    private Integer id;

    // 电影名
    @Schema(description = "电影名", example = "哪吒2")
    private String name;

    // 简介
    @Schema(description = "电影简介", example = "哪吒2是一部精美的电影吗")
    private String description;

    // 电影地区id
    @Schema(description = "电影地区id", example = "1")
    private Integer zoneId;

    // 浏览数
    @Schema(description = "电影浏览数", example = "114514")
    private Long view;

    // 海报链接
    @Schema(description = "电影海报", example = "")
    private String poster;

    // 观看权限(0:普通 1:VIP)
    @Schema(description = "观影权限", example = "")
    private Boolean permission;

    // 上线日期
    @Schema(description = "上线日期", example = "2025-05-31")
    private Date releaseDate;

    // 导演ID
    @Schema(description = "导演ID")
    private Integer directorId;

    // 导演姓名
    @Schema(description = "导演姓名")
    private String directorName;

    // 主演ID
    @Schema(description = "主演ID")
    private Integer actorId;

    // 主演姓名
    @Schema(description = "主演姓名")
    private String actorName;
}
