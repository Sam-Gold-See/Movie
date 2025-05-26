package com.samgoldsee.movie.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.samgoldsee.movie.config.AliPayConfig;
import com.samgoldsee.movie.constant.AliPayConstant;
import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.entity.AliPay;
import com.samgoldsee.movie.exception.AliPayException;
import com.samgoldsee.movie.mapper.AliPayRecordMapper;
import com.samgoldsee.movie.service.AliPayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class AliPayServiceImpl implements AliPayService {

    @Resource
    private AliPayConfig aliPayConfig;

    @Resource
    private AliPayRecordMapper aliPayRecordMapper;

    /**
     * 发起支付请求
     *
     * @param aliPay 支付宝订单对象
     * @param httpResponse response
     */
    @Override
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception{
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
        bizContent.put("out_trade_no", aliPay.getTraceNo());
        bizContent.put("total_amount", aliPay.getTotalAmount());
        bizContent.put("subject", aliPay.getSubject());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        request.setBizContent(bizContent.toJSONString());

        String form;

        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            throw new AliPayException(MessageConstant.ALIPAY_ERROR + e.getMessage());
        }

        httpResponse.setContentType("text/html;charset=" + aliPayConfig.getCharset());

        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
}
