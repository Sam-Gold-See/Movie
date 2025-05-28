package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.result.Result;
import com.samgoldsee.movie.service.RecordService;
import com.samgoldsee.movie.vo.MovieRankVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "播放记录管理接口")
@RequestMapping("/record")
public class RecordController {

    @Resource
    private RecordService recordService;

    /**
     * 查询全部播放情况
     */
    @GetMapping("/rankWeek")
    @Operation(summary = "查询全部播放排行")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = MovieRankVOPageResultResult.class)
    ))
    public Result<PageResult<MovieRankVO>> rankAll(){
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})查询全部电影播放排行", userSession.getId());
        PageResult<MovieRankVO> result = recordService.rankAll();
        return Result.success(result);
    }

    @Schema(name = "Result<PageResult<MovieRankVO>>", description = "包含MovieRankVO的多数据统一响应对象")
    private static class MovieRankVOPageResultResult extends Result<PageResult<MovieRankVO>> {
    }
}
