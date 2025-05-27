package com.samgoldsee.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AliPayOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 订单id
    private Long id;

    // 商户订单号
    private String tradeNo;

    // 用户ID
    private Integer userId;

    // 支付金额
    private BigDecimal totalAmount;

    // 订单主题
    private String subject;

    // 支付宝订单号
    private String aliPayTradeNo;

    // 订单状态(0:未完成 1:完成)
    private Boolean status;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;
}
