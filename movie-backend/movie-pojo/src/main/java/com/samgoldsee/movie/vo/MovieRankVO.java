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
@Schema(description = "电影详情VO", title = "MovieRankVO")
public class MovieRankVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 电影ID
    @Schema(description = "电影ID", example = "1")
    private Integer movieId;

    // 电影名
    @Schema(description = "电影名", example = "哪吒2")
    private String movieName;

    // 电影评分
    @Schema(description = "电影评分", example = "0")
    private Double avgRating;

    // 电影播放次数
    @Schema(description = "电影评分", example = "166")
    private Integer recordCnt;
}
