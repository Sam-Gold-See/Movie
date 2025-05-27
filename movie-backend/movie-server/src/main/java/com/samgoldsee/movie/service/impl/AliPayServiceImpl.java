package com.samgoldsee.movie.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import com.samgoldsee.movie.config.AliPayConfig;
import com.samgoldsee.movie.constant.AliPayConstant;
import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.dto.AliPayDTO;
import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.entity.AliPayOrder;
import com.samgoldsee.movie.entity.User;
import com.samgoldsee.movie.exception.AliPayException;
import com.samgoldsee.movie.mapper.AliPayOrderMapper;
import com.samgoldsee.movie.mapper.UserMapper;
import com.samgoldsee.movie.service.AliPayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AliPayServiceImpl implements AliPayService {

    @Resource
    private AliPayConfig aliPayConfig;

    @Resource
    private AliPayOrderMapper aliPayOrderMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 发起支付请求
     *
     * @param aliPayDTO    支付宝订单对象
     * @param httpResponse response
     */
    @Override
    public void pay(AliPayDTO aliPayDTO, HttpServletResponse httpResponse) throws Exception {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AliPayOrder order = AliPayOrder.builder()
                .tradeNo(aliPayDTO.getTradeNo())
                .totalAmount(BigDecimal.valueOf(aliPayDTO.getTotalAmount()))
                .subject(aliPayDTO.getSubject())
                .userId(userSession.getId())
                .status(false)
                .build();
        aliPayOrderMapper.insert(order);

        AlipayClient alipayClient = new DefaultAlipayClient(
                AliPayConstant.GATEWAY_URL,
                aliPayConfig.getAppId(),
                aliPayConfig.getMerchantPrivateKey(),
                AliPayConstant.FORMAT,
                AliPayConstant.CHARSET,
                aliPayConfig.getAlipayPublicKey(),
                aliPayConfig.getSignType());

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", aliPayDTO.getTradeNo());
        bizContent.put("total_amount", aliPayDTO.getTotalAmount());
        bizContent.put("subject", aliPayDTO.getSubject());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        request.setBizContent(bizContent.toJSONString());

        String form;

        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            throw new AliPayException(MessageConstant.ALIPAY_ERROR + ":" + e.getMessage());
        }

        httpResponse.setContentType("text/html;charset=" + aliPayConfig.getCharset());

        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    /**
     * 支付宝支付异步回调
     *
     * @param request request
     */
    @Override
    public User payNotify(HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet())
            params.put(name, request.getParameter(name));
        log.info("支付宝异步回调参数: {}", params);

        String tradeStatus = params.get("trade_status");
        if (!"TRADE_SUCCESS".equals(tradeStatus)) {
            throw new AliPayException(MessageConstant.ALIPAY_ERROR + ":" + tradeStatus);
        }

        // 验签
        boolean signVerified = Factory.Payment.Common().verifyNotify(params);
        if (!signVerified)
            throw new AliPayException(MessageConstant.ALIPAY_ERROR + ": 回调验签失败");

        String aliPayTradeNo = params.get("out_trade_no");

        AliPayOrder aliPayOrder = aliPayOrderMapper.getByTradeNo(aliPayTradeNo);

        aliPayOrderMapper.update(AliPayOrder.builder()
                .id(aliPayOrder.getId())
                .aliPayTradeNo(aliPayTradeNo)
                .status(true)
                .build());

        User user = User.builder()
                .id(aliPayOrder.getUserId())
                .type(true)
                .build();
        userMapper.update(user);

        return user;
    }
}
