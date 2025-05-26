package com.samgoldsee.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "支付订单DTO", title = "AliPayDTO")
public class AliPayDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 订单编号
    private String traceNo;

    // 商品金额
    private Double totalAmount;

    // 商品名称
    private String subject;

    // 订单追踪号
    private String alipayTraceNo;
}
