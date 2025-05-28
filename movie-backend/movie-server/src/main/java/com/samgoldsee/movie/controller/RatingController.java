package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.result.Result;
import com.samgoldsee.movie.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "评分管理接口")
@RequestMapping("/rating")
public class RatingController {

    @Resource
    private RatingService ratingService;

    /**
     * 用户评分
     *
     * @param movieId 电影ID
     * @param rating  评分
     */
    @PostMapping("/upload")
    @Operation(description = "用户评分")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Result.class)
    ))
    public Result<String> upload(Integer movieId, Integer rating) {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})给电影(id:{})评分:{}", userSession.getId(), movieId, rating);
        ratingService.upload(userSession.getId(), movieId, rating);
        return Result.success();
    }
}
