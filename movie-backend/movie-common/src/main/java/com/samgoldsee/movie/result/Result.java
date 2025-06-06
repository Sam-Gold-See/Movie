package com.samgoldsee.movie.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一响应对象
 */
@Data
@Schema(description = "全局统一响应结果", title = "Result")
public class Result<T> implements Serializable {

    @Schema(description = "状态码", example = "1")
    private Integer code;

    @Schema(description = "报错信息")
    private String msg;

    @Schema(description = "响应数据")
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = object;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = success();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
