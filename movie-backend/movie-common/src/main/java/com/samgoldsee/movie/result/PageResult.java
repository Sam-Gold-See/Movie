package com.samgoldsee.movie.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "多数据查询响应对象", title = "PageResult")
public class PageResult<T> implements Serializable {

    @Schema(description = "总记录数", example = "5")
    private long total;

    @Schema(description = "记录数据")
    private List<T> records;
}
