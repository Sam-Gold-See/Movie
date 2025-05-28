package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.entity.MovieType;
import com.samgoldsee.movie.entity.MovieZone;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.result.Result;
import com.samgoldsee.movie.service.MovieService;
import com.samgoldsee.movie.vo.MovieDetailVO;
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
            schema = @Schema(implementation = MovieDetailVOResult.class)
    ))
    public Result<MovieDetailVO> getDetail(@RequestParam(name = "id") Integer id) {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})查询电影(id:{})详情", userSession.getId(), id);
        MovieDetailVO movieDetailVO = movieService.getDetailById(id);
        return Result.success(movieDetailVO);
    }

    @Schema(name = "Result<MovieVO>", description = "包含MovieDetailVO的统一响应对象")
    private static class MovieDetailVOResult extends Result<MovieDetailVO> {
    }

    /**
     * 播放电影
     *
     * @param id 电影ID
     */
    @GetMapping("/play")
    @Operation(summary = "播放电影")
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

    /**
     * 查询电影种类分类
     */
    @GetMapping("/getType")
    @Operation(summary = "查询电影种类分类")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = MovieTypePageResultResult.class)
    ))
    public Result<PageResult<MovieType>> getType() {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})查询电影类型", userSession.getId());
        PageResult<MovieType> res = movieService.getType();
        return Result.success(res);
    }

    @Schema(name = "Result<PageResult<MovieType>>", description = "包含MovieType的多数据统一响应对象")
    private static class MovieTypePageResultResult extends Result<PageResult<MovieType>> {
    }

    /**
     * 查询电影地区分类
     */
    @GetMapping("/getZone")
    @Operation(summary = "查询电影地区分类")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = MovieZonePageResultResult.class)
    ))
    public Result<PageResult<MovieZone>> getZone() {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})查询电影地区", userSession.getId());
        PageResult<MovieZone> res = movieService.getZone();
        return Result.success(res);
    }

    @Schema(name = "Result<PageResult<MovieZone>>", description = "包含MovieZone的多数据统一响应对象")
    private static class MovieZonePageResultResult extends Result<PageResult<MovieZone>> {
    }

    /**
     * 获取全部电影
     */
    @GetMapping("/all")
    @Operation(summary = "获取全部电影")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = MovieVOPageResultResult.class)
    ))
    public Result<PageResult<MovieVO>> getAll() {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})正在获取全部电影", userSession.getId());
        PageResult<MovieVO> pageResult = movieService.getAll();
        return Result.success(pageResult);
    }

    /**
     * 根据条件查询电影
     *
     * @param typeId 种类ID
     * @param zoneId 地区ID
     */
    @GetMapping("/optionSearch")
    @Operation(summary = "查询符合条件电影")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = MovieVOPageResultResult.class)
    ))
    public Result<PageResult<MovieVO>> optionSearch(Integer typeId, Integer zoneId) {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})正在查询符合条件电影:{},{}", userSession.getId(), typeId, zoneId);
        PageResult<MovieVO> pageResult = movieService.optionSearch(typeId, zoneId);
        return Result.success(pageResult);
    }
}
