package com.samgoldsee.movie.service;

import com.samgoldsee.movie.entity.AliPay;
import jakarta.servlet.http.HttpServletResponse;

public interface AliPayService {

    /**
     * 发起支付请求
     *
     * @param aliPay 支付宝订单对象
     * @param httpResponse response
     */
    void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception;
}
