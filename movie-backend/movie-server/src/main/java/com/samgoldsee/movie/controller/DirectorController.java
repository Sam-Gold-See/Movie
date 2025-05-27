package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.entity.Director;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.result.Result;
import com.samgoldsee.movie.service.DirectorService;
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
@Tag(name = "导演管理接口")
@RequestMapping("/director")
public class DirectorController {

    @Resource
    private DirectorService directorService;

    /**
     * 获取全部导演信息
     */
    @GetMapping("/all")
    @Operation(summary = "查询所有导演")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = DirectorPageResultResult.class)
    ))
    public Result<PageResult<Director>> all() {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})正在查询所有演员", userSession.getId());
        PageResult<Director> pageResult = directorService.getAll();
        return Result.success(pageResult);
    }

    @Schema(name = "Result<PageResult<Director>>", description = "包含Director的多数据响应对象")
    private static class DirectorPageResultResult extends Result<PageResult<Director>> {
    }
}
