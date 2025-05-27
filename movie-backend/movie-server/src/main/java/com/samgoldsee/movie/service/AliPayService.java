package com.samgoldsee.movie.service;

import com.samgoldsee.movie.dto.AliPayDTO;
import com.samgoldsee.movie.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AliPayService {

    /**
     * 发起支付请求
     *
     * @param aliPayDTO    支付宝订单对象
     * @param httpResponse response
     */
    void pay(AliPayDTO aliPayDTO, HttpServletResponse httpResponse) throws Exception;

    /**
     * 支付宝支付异步回调
     *
     * @param request request
     */
    User payNotify(HttpServletRequest request) throws Exception;
}
