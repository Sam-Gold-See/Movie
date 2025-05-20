package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.result.Result;
import com.samgoldsee.movie.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/email")
@Tag(name = "EmailController")
public class EmailController {

    @Resource
    private EmailService emailService;

    /**
     * 请求验证码
     *
     * @param email 目标邮箱
     */
    @GetMapping("/sendCode")
    @Operation(summary = "sendCode", description = "向指定邮箱发送验证码，用于用户注册或登录验证")
    @Parameters({
            @Parameter(name = "email", description = "目标邮箱地址", required = true, example = "user@example.com", in = ParameterIn.QUERY)
    })
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            schema = @Schema(implementation = Result.class)
    ))
    public Result<String> sendCode(@RequestParam String email) {
        log.info("请求验证码:{}", email);
        emailService.sendCode(email);
        return Result.success();
    }
}
