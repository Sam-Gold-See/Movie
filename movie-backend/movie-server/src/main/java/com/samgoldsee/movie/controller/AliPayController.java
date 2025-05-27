package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.dto.AliPayDTO;
import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.entity.User;
import com.samgoldsee.movie.result.Result;
import com.samgoldsee.movie.service.AliPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "阿里支付功能接口")
@Slf4j
@RestController
@RequestMapping("/alipay")
@Transactional(rollbackFor = Exception.class)
public class AliPayController {

    @Resource
    private AliPayService aliPayService;

    /**
     * 发起支付请求
     *
     * @param aliPayDTO    支付宝订单对象
     * @param httpResponse response
     */
    @Operation(summary = "发起支付请求")
    @GetMapping("/pay")
    public void pay(AliPayDTO aliPayDTO, HttpServletResponse httpResponse) throws Exception {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})正在提交升级账户权限账单:{}", userSession.getId(), aliPayDTO);
        aliPayService.pay(aliPayDTO, httpResponse);
    }

    /**
     * 支付宝支付异步回调
     *
     * @param request request
     */
    @Operation(summary = "支付回调")
    @PostMapping("/notify")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Result.class)
    ))
    public Result<String> payNotify(HttpServletRequest request) throws Exception {
        User user = aliPayService.payNotify(request);
        log.info("用户(id:{})完成账号权限升级", user.getId());
        return Result.success();
    }
}