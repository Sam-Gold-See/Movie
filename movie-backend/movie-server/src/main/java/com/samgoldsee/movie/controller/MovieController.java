package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.result.Result;
import com.samgoldsee.movie.service.MovieService;
import com.samgoldsee.movie.vo.MovieVO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "电影管理接口")
@RequestMapping("/movie")
public class MovieController {

    @Resource
    private MovieService movieService;

    /**
     * 获取随机推荐电影
     */
    @GetMapping("/rec")
    @Operation(summary = "随机推荐电影")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = MovieVOPageResultResult.class)
    ))
    public Result<PageResult<MovieVO>> getRec() {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})正在获取推荐电影", userSession.getId());
        PageResult<MovieVO> pageResult = movieService.getRec();
        return Result.success(pageResult);
    }

    @Schema(name = "Result<PageResult<MovieVO>>", description = "包含MovieVO的多数据响应对象")
    private static class MovieVOPageResultResult extends Result<PageResult<MovieVO>> {
    }

    /**
     * 获取具体电影信息
     *
     * @param id 电影ID
     */
    @GetMapping("/detail")
    @Operation(summary = "获取具体电影信息")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = MovieVOResult.class)
    ))
    public Result<MovieVO> getDetail(@RequestParam(name = "id") Integer id) {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})查询电影(id:{})详情", userSession.getId(), id);
        MovieVO movieVO = movieService.getById(id);
        return Result.success(movieVO);
    }

    @Schema(name = "Result<MovieVO>", description = "包含MovieVO的统一响应对象")
    private static class MovieVOResult extends Result<MovieVO> {
    }

    /**
     * 播放电影
     *
     * @param id 电影ID
     */
    @GetMapping("/play")
    @Operation(summary = "电影")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Result.class)
    ))
    public Result<String> play(Integer id) {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})播放电影(id:{})", userSession.getId(), id);
        movieService.play(userSession.getId(), id);
        return Result.success();
    }
}
