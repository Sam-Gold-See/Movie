package com.samgoldsee.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "条件搜索DTO", title = "OperationSearchDTO")
public class OperationSearchDTO {

    // 电影类型ID
    @Schema(description = "电影类型ID")
    private Integer typeId;

    // 电影地区ID
    @Schema(description = "电影地区ID")
    private Integer zoneId;

    // 电影主演ID
    @Schema(description = "电影主演ID")
    private Integer actorId;

    // 电影导演ID
    @Schema(description = "电影导演ID")
    private Integer directorId;
}
