package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.annotation.AutoFill;
import com.samgoldsee.movie.entity.AliPayOrder;
import com.samgoldsee.movie.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AliPayRecordMapper {

    /**
     * 新增订单
     *
     * @param order 支付宝支付订单
     */
    @AutoFill(operation = OperationType.INSERT)
    @Insert("INSERT INTO `ali_pay_order` " +
            "(`trace_no`, `user_id`, `total_amount`, `subject`, `ali_pay_trade_no`, `status`, `create_time`, `update_time`) " +
            "VALUES (#{traceNo}, #{userId}, #{totalAmount}, #{subject}, #{aliPayTradeNo}, #{status}, #{createTime}, #{updateTime})")
    void insert(AliPayOrder order);
}
