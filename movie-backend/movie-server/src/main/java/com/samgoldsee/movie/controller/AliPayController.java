package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.config.AliPayConfig;
import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.entity.AliPay;
import com.samgoldsee.movie.service.AliPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Resource
    private AliPayConfig aliPayConfig;

    /**
     * 发起支付请求
     *
     * @param aliPay       支付宝订单对象
     * @param httpResponse response
     */
    @Operation(summary = "发起支付请求")
    @GetMapping("/pay")
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})正在提交升级账户权限账单:{}", userSession.getId(), aliPay);
        aliPayService.pay(aliPay, httpResponse);
    }
}